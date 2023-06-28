package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Промокод
 */
@Data
@AllArgsConstructor
public class PromoCode {
    private String promoCode;
    private int discount;
}
