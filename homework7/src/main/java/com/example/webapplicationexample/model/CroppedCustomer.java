package com.example.webapplicationexample.model;

import lombok.Data;

/**
 * Обрезанный пользователей
 */
@Data
public class CroppedCustomer{
    private String name;
    private String email;
    private Cart cart;

    public CroppedCustomer(Customer customer){
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.cart = customer.getCart();
    }

}
