package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Transfer;

import java.math.BigDecimal;

/**
 * Сервис по оплате товаров
 */
public interface PaymentService {
    /**
     * Совершает оплату товара
     * @param transfer - информация о платеже
     * @return успешность операции
     */
    BigDecimal pay(Transfer transfer);
}
