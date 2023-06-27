package com.example.webapplicationexample.service;

import com.example.webapplicationexample.exception.InsufficientFundsException;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.repository.LocalCustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Сервис по оплате товаров
 */
@Service
public class LocalPaymentService implements PaymentService {

    LocalCustomerRepository localCustomerRepository;

    public LocalPaymentService(LocalCustomerRepository localCustomerRepository) {
        this.localCustomerRepository = localCustomerRepository;
    }

    public boolean pay(Transfer transfer) {
        Optional<Customer> customer = localCustomerRepository.getById(transfer.getIdUser());
        if (customer.isPresent()
                && customer.get().getCart() != null
                && customer.get().getCart().getProducts() != null) {
            BigDecimal fullPrice = customer.get().getCart().getProducts().stream()
                    .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getAmount())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (transfer.getSum() != null) {
                if (transfer.getSum().compareTo(fullPrice) == 1) {
                    return true;
                } else {
                    throw new InsufficientFundsException();
                }
            }
        }
        return false;
    }
}
