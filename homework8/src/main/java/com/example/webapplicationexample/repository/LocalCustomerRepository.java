package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Осуществляет операции с пользователем
 */
public class LocalCustomerRepository implements CustomerRepository{

    CartRepository cartRepository;

    public LocalCustomerRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    List<Customer> customers = new ArrayList<>();

    @Override
    public long registration(Customer customer) {
        long id = generateId();
        customer.setId(id);
        customer.setCart(cartRepository.generate(id));
        customers.add(customer);
        return id;
    }

    @Override
    public Optional<Customer> getById(long id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findAny();
    }

    @Override
    public boolean deleteById(long id) {
        return customers.removeIf(customer -> customer.getId() == id);
    }

    private long generateId() {
        Random random = new Random();
        int low = 1;
        int high = 1_000_000;
        return random.nextLong(high - low) + low;
    }
}
