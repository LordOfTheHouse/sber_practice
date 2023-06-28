package com.example.webapplicationexample.service;

import com.example.webapplicationexample.exception.AccountNotDefined;
import com.example.webapplicationexample.exception.InsufficientFundsException;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.proxy.BankProxy;
import com.example.webapplicationexample.repository.CartRepository;
import com.example.webapplicationexample.repository.CustomerRepository;
import com.example.webapplicationexample.repository.PromoCodesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * Сервис по оплате товаров
 */
@Service
public class LocalPaymentService implements PaymentService {
    private CartRepository cartRepository;

    public LocalPaymentService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public BigDecimal pay(Transfer transfer) {
        return cartRepository.getSumPriceCart(transfer.getIdUser());
    }
}
