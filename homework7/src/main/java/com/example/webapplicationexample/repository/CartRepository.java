package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;

import java.util.Optional;

/**
 * Взаимодействие с сущностью корзины
 */
public interface CartRepository {
    /**
     * Добавляет продукт в корзину по id
     * @param id - индификатор корзины
     * @param product - продукт
     * @return обновленную корзину
     */
    Optional<Cart> addProductInCartById(long id, Product product);

    /**
     * Обновляет информацию о продукте
     * @param idCart - индификатор корзины
     * @param idProduct - индификатор продукта
     * @param amount - количество продукта
     * @return обновленную корзину
     */
    Optional<Cart> updateAmountProduct(long idCart, long idProduct,int amount);

    /**
     * Удаляет продукт из корзины
     * @param idCart - индификатор корзины
     * @param idProduct - индификатор продукта
     * @return статус операции
     */
    boolean deleteProduct(long idCart, long idProduct);

    /**
     * Генерирует простую корзину
     * @return корзину
     */
    Cart generate(long id);

}
