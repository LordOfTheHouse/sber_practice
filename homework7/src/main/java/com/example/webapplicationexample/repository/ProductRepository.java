package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Product;

import java.util.List;
import java.util.Optional;

/**
 *  Осуществляет операции с продуктами
 */
public interface ProductRepository {
    /**
     * Добавляет продукт
     * @param product - информация о продукте
     * @return id
     */
    long save(Product product);

    /**
     * Ищет продукти по ид
     * @param id - индификатор
     * @return продукт
     */
    Optional<Product> findById(long id);

    /**
     * Ищет продукты по имени
     * @param name - наименование
     * @return продукты
     */
    List<Product> findAll(String name);

    /**
     * Обновляет информацию о продукте
     * @param product - продукт
     * @return успешность операции
     */
    boolean update(Product product);

    /**
     * Удаляет пользователя по id
     * @param id - индификатор
     * @return успешность операции
     */
    boolean deleteById(long id);
}
