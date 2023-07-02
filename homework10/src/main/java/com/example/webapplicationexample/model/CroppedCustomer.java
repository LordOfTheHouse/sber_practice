package com.example.webapplicationexample.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Обрезанный пользователей
 */
@Data
public class CroppedCustomer{

    private String name;
    private String email;
    private List<CroppedProduct> cart;

    public CroppedCustomer(Customer customer){
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.cart = new ArrayList<>();
    }

}
