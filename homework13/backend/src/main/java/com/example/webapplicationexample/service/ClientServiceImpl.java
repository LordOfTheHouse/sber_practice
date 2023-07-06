package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.User;
import com.example.webapplicationexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервис для взаимодействия с клиентом
 */
@Service
public class ClientServiceImpl implements ClientService {

    UserRepository userRepository;
    @Autowired
    public ClientServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long save(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public boolean deleteById(long userId) {
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public boolean existsById(long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findCustomerByEmail(email);
    }

}
