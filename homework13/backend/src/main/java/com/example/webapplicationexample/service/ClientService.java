package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.User;

import java.util.Optional;

/**
 * Сервис для взаимодействия с клиентом
 */
public interface ClientService {

    long save(User user);

    Optional<User> findById(long userId);
    boolean deleteById(long userId);
    boolean existsById(long userId);

    Optional<User> findByEmail(String name);

}
