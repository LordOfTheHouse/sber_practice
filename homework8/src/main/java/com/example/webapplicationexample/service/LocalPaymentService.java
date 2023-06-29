package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.proxy.BankProxy;
import com.example.webapplicationexample.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Сервис по оплате товаров
 */
@Service
public class LocalPaymentService implements PaymentService {
    private CartRepository cartRepository;
    private BankProxy bankProxy;

    public LocalPaymentService(CartRepository cartRepository, BankProxy bankProxy) {
        this.cartRepository = cartRepository;
        this.bankProxy = bankProxy;
    }

    @Transactional
    public BigDecimal pay(Transfer transfer) {
        return bankProxy.checkMeansCustomer(transfer.getNumberCart(),
                cartRepository.getSumPriceCart(transfer.getIdUser()));
    }
}
