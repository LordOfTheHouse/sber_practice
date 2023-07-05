package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервис для взаимодействия с клиентом
 */
@Service
public class ClientServiceImpl implements ClientService {

    CustomerRepository customerRepository;
    @Autowired
    public ClientServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public long save(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @Override
    public Optional<Customer> findById(long userId) {
        return customerRepository.findById(userId);
    }

    @Transactional
    @Override
    public boolean deleteById(long userId) {
        customerRepository.deleteById(userId);
        return true;
    }

    @Override
    public boolean existsById(long userId) {
        return customerRepository.existsById(userId);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

}
