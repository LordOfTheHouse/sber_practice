package com.example.webapplicationexample.service;

import com.example.webapplicationexample.exception.AccountNotDefined;
import com.example.webapplicationexample.exception.ProductNotInStock;
import com.example.webapplicationexample.model.*;
import com.example.webapplicationexample.proxy.BankProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.math.BigDecimal.*;

/**
 * Сервис для осуществления платежа
 */
@Slf4j
@Service
public class PaymentRealiseService implements PaymentService{
    private CartService cartService;
    private ProductService productService;
    private BankProxy bankProxy;
    private PromocodesService promocodesService;
    @Autowired
    public PaymentRealiseService(CartService cartService, ProductService productService
            , BankProxy bankProxy,  PromocodesService promocodesService) {
        this.cartService = cartService;
        this.productService = productService;
        this.bankProxy = bankProxy;
        this.promocodesService = promocodesService;
    }

    @Transactional
    @Override
    public BigDecimal pay(Transfer transfer) {

        List<CroppedProduct> productList = cartService.findProductsInCart(transfer.getIdUser());

        isEmptyCart(productList);

        BankAccount bankAccount = new BankAccount(transfer.getNumberCart(), BigDecimal.valueOf(0));

        List<CroppedProduct> productsInWarehouse = productList.stream()
                .filter(checkAvailabilityAmountInHomework())
                .map(CountsAmountToPaid(transfer, bankAccount))
                .toList();

        isUpdateProductsHomework(productList, productsInWarehouse);

        Optional<Promocode> promocode = promocodesService.findPromocode(transfer.getPromoCode());
        if(promocode.isEmpty()){
            return checkMoneyAccauntClient(transfer, bankAccount);
        }

        double coefficient = 1-(promocode.get().getPercent()/100.0);

        bankAccount.setSum(getFinalPrice(bankAccount, coefficient));

        return checkMoneyAccauntClient(transfer, bankAccount);

    }


    /**
     * Проверяет хватат ли у пользователя денег на покупку
     */
    private BigDecimal checkMoneyAccauntClient(Transfer transfer, BankAccount bankAccount) {
        return bankProxy.checkMeansCustomer(transfer.getNumberCart(), bankAccount.getSum());
    }

    /**
     * Возрващает итоговую цену
     */
    private BigDecimal getFinalPrice(BankAccount bankAccount, double coefficient) {
        return bankAccount.getSum().multiply(BigDecimal.valueOf(coefficient));
    }

    /**
     * Проверяет все ли товары были изъяты со склада
     */
    private void isUpdateProductsHomework(List<CroppedProduct> productList, List<CroppedProduct> productsInWarehouse) {
        if(productList.size() != productsInWarehouse.size()){
            throw new ProductNotInStock("Товара на складе не достаточно");
        }
    }

    /**
     * Подсчитывает сумму к оплате
     */
    private Function<CroppedProduct, CroppedProduct> CountsAmountToPaid(Transfer transfer, BankAccount bankAccount) {
        return product -> {

            Optional<Product> productInWarehouse = productService.findById(product.getId());
            double priceProduct = productInWarehouse.get().getPrice().doubleValue();
            bankAccount.setSum(valueOf(priceProduct * product.getAmount()).add(bankAccount.getSum()));

            updateDataInBD(transfer, product, productInWarehouse);

            return product;
        };
    }

    /**
     * Обновялет данные о покупке в БД
     */
    private void updateDataInBD(Transfer transfer, CroppedProduct product, Optional<Product> productInWarehouse) {
        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setAmount(productInWarehouse.get().getAmount() - product.getAmount());

        productService.update(newProduct);
        cartService.deleteProductInCart(transfer.getIdUser(), product.getId());
    }

    /**
     * Проверяет наличие товара на складе
     */
    private Predicate<CroppedProduct> checkAvailabilityAmountInHomework() {
        return product -> {
            log.info("{} {}", productService.findById(product.getId()).get().getAmount(), product.getAmount());
            return productService.findById(product.getId()).get().getAmount() >= product.getAmount();
        };
    }

    /**
     * Проверяет, что корзщина не пуста
     * @param productList
     */
    private void isEmptyCart(List<CroppedProduct> productList) {
        if(productList.isEmpty()){
            throw new AccountNotDefined("Корзина пуста");
        }
    }
}
