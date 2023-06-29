package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Продукт
 */
@Data
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private BigDecimal price;
    private int amount;
}
