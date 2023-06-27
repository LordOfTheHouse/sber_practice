package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Информация о платеже
 */
@Data
@AllArgsConstructor
public class Transfer {
    BigDecimal sum;
    long idUser;
}
