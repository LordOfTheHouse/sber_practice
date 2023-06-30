package com.example.webapplicationexample.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Корзина
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products_carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Customer client;
    private int count;
}
