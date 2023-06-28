package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.proxy.BankProxy;
import com.example.webapplicationexample.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Сервис по оплате товаров
 */
@Service
public class LocalPaymentService implements PaymentService {

    private CustomerRepository customerRepository;
    private BankProxy bankProxy;

    public LocalPaymentService(CustomerRepository customerRepository,
                               BankProxy bankProxy) {
        this.customerRepository = customerRepository;
        this.bankProxy = bankProxy;
    }

    public boolean pay(Transfer transfer) {
        Optional<Customer> customer = customerRepository.getById(transfer.getIdUser());
        if (customer.isPresent()
                && customer.get().getCart() != null
                && customer.get().getCart().getProducts() != null) {
            BigDecimal fullPrice = customer.get().getCart().getProducts().stream()
                    .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getAmount())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (transfer.getNumberCart() != null) {
               return bankProxy.checkMeansCustomer(transfer.getNumberCart(), fullPrice);
            }
        }
        return false;
    }
}
