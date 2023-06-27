package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Корзина
 */
@Data
@AllArgsConstructor
public class Cart {
    private Long id;
    private List<Product> products;
    private String promoCode;
}
