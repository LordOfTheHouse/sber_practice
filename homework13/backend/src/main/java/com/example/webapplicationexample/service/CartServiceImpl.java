package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.CroppedProduct;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с Корзиной
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService{
    CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;

    }

    @Override
    public boolean saveProductInCart(long userId, Product product) {

        Customer customer = new Customer();
        customer.setId(userId);
        Optional<Cart> cart = cartRepository.findByProductAndClient(product, customer);
        if(cart.isEmpty()){
            Cart newCart = new Cart();
            newCart.setProduct(product);
            newCart.setClient(customer);
            newCart.setCount(product.getAmount());
            cartRepository.save(newCart);
        } else {
            cart.get().setCount(cart.get().getCount() + product.getAmount());
            cartRepository.save(cart.get());
        }
        return true;
    }

    @Override
    public boolean updateAmountProduct(long userId, Product product) {
        Customer customer = new Customer();
        customer.setId(userId);
        Optional<Cart> cart = cartRepository.findByProductAndClient(product,customer);
        if(cart.isPresent()){
            cart.get().setCount(product.getAmount());
            cartRepository.save(cart.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProductInCart(long idUser, long idProduct) {
        Product product = new Product();
        product.setId(idProduct);
        Customer customer = new Customer();
        customer.setId(idUser);
        Optional<Cart> cart = cartRepository.findByProductAndClient(product, customer);
        if(cart.isEmpty()){
            return false;
        }
        cartRepository.deleteById(cart.get().getId());
        return true;
    }

    @Override
    public List<CroppedProduct> findProductsInCart(long userId) {
        Customer customer = new Customer();
        customer.setId(userId);

        return cartRepository.findByClient(customer).stream()
                .map(cart -> {
                            CroppedProduct croppedProduct = new CroppedProduct(cart.getProduct());
                            croppedProduct.setAmount(cart.getCount());
                            return croppedProduct;
                        }
                )
                .toList();
    }

    @Override
    public boolean deleteAllClient(Customer customer) {
        cartRepository.deleteAllByClient(customer);
        return true;
    }
}
