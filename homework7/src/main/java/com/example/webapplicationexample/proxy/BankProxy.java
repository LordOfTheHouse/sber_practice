package com.example.webapplicationexample.proxy;

import java.math.BigDecimal;

/**
 * Банк пользователя
 */
public interface BankProxy {
    /**
     * Проверяет, что у пользователя достаточно средств для совершения трансфера
     * @return true если средств достаточно
     */
    boolean checkMeansCustomer(String numberCard, BigDecimal sum);
}
