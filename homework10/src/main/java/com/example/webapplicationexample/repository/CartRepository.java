package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Взаимодействие с сущностью корзины
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByProductAndClient(Product product, Customer customer);
    List<Cart> findByClient(Customer customer);
    void deleteAllByClient(Customer customer);

}
