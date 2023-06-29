package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Счет пользователя
 */
@Data
@AllArgsConstructor
public class BankAccount {
    private String numberBankCard;
    private BigDecimal sum;
}
