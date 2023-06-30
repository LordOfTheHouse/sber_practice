package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с клиентом
 */
@Service
public class ClientServiceImpl implements ClientService{

    CustomerRepository customerRepository;
    CartService cartService;
    @Autowired
    public ClientServiceImpl(CustomerRepository customerRepository, CartService cartService) {
        this.customerRepository = customerRepository;
        this.cartService = cartService;
    }

    @Override
    public long save(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @Override
    public Optional<Customer> findById(long userId) {
        return customerRepository.findById(userId);
    }

    @Override
    @Transactional
    public boolean deleteById(long userId) {
        Customer customer = new Customer();
        customer.setId(userId);
        cartService.deleteAllClient(customer);
        customerRepository.deleteById(userId);
        return true;
    }

}
