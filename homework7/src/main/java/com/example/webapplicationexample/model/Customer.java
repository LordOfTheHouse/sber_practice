package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Покупатель
 */
@Data
@AllArgsConstructor
public class Customer {
    private long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Cart cart;

}
