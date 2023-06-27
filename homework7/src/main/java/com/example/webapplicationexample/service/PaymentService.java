package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Transfer;

/**
 * Сервис по оплате товаров
 */
public interface PaymentService {
    /**
     * Совершает оплату товара
     * @param transfer - информация о платеже
     * @return успешность операции
     */
    boolean pay(Transfer transfer);
}
