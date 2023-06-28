package com.example.webapplicationexample.service;

import com.example.webapplicationexample.exception.InsufficientFundsException;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.proxy.BankProxy;
import com.example.webapplicationexample.repository.CustomerRepository;
import com.example.webapplicationexample.repository.PromoCodesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Сервис по оплате товаров
 */
@Service
public class LocalPaymentService implements PaymentService {

    private CustomerRepository customerRepository;
    private BankProxy bankProxy;
    private PromoCodesRepository promoCodesRepository;

    public LocalPaymentService(CustomerRepository customerRepository,
                               BankProxy bankProxy, PromoCodesRepository promoCodesRepository) {
        this.customerRepository = customerRepository;
        this.bankProxy = bankProxy;
        this.promoCodesRepository = promoCodesRepository;
    }

    public BigDecimal pay(Transfer transfer) {
        Optional<Customer> customer = customerRepository.getById(transfer.getIdUser());
        if (customer.isPresent()
                && customer.get().getCart() != null
                && customer.get().getCart().getProducts() != null) {
            BigDecimal fullPrice = customer.get().getCart().getProducts().stream()
                    .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getAmount())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            int promo = promoCodesRepository.getDiscount(customer.get().getCart().getPromoCode());
            double coefficient = (double) (100 - promo) / 100;

            fullPrice = fullPrice.multiply(BigDecimal.valueOf(coefficient));
            if (transfer.getNumberCart() != null) {
                if (bankProxy.checkMeansCustomer(transfer.getNumberCart(), fullPrice)) {
                    return fullPrice;
                }
            }
        }
        throw new InsufficientFundsException();
    }
}
