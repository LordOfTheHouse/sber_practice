package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимоднействия с продуктами
 */
public interface ProductService {
    long save(Product product);

    Optional<Product> findById(long id);

    List<Product> findAll(String name);

    boolean update(Product product);

    boolean deleteById(long id);
}
