package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с клиентом
 */
public interface ClientService {

    long save(Customer customer);

    Optional<Customer> findById(long userId);
    boolean deleteById(long userId);
    boolean existsById(long userId);

}
