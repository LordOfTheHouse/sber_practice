package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;

import java.util.List;

/**
 * Сервис для взаимодействия с корзиной
 */
public interface CartService {
    boolean saveProductInCart(long userId, Product product);
    boolean updateAmountProduct(long userId, Product product);
    List<Product> findProductsInCart(long userId);

    boolean deleteAllClient(Customer customer);
    boolean deleteProductInCart(long userId, long productId);
}
