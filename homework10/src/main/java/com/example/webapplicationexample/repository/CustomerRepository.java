package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Взаимодействие с сущностью покупателя
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
