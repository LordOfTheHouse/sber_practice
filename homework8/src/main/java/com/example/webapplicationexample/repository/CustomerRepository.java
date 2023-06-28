package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Customer;

import java.util.Optional;

/**
 * Взаимодействие с сущностью покупателя
 */
public interface CustomerRepository {
    /**
     * Регистрация
     * @param customer - информация о пользщователе
     * @return индификатор
     */
    long registration(Customer customer);

    /**
     * Возвращает пользователя по индификатору
     * @param id - индификатор
     * @return пользователь
     */
    Optional<Customer> getById(long id);
    boolean deleteById(long id);
}
