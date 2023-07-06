package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.CroppedProduct;
import com.example.webapplicationexample.model.User;
import com.example.webapplicationexample.model.Product;

import java.util.List;

/**
 * Сервис для взаимодействия с корзиной
 */
public interface CartService {
    boolean saveProductInCart(long userId, Product product);
    boolean updateAmountProduct(long userId, Product product);
    List<CroppedProduct> findProductsInCart(long userId);

    boolean deleteAllClient(User user);
    boolean deleteProductInCart(long userId, long productId);
}
