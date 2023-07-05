package com.example.webapplicationexample.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Обрезанный пользователей
 */
@Data
public class CroppedCustomer{

    private long id;
    private String name;
    private String email;
    private List<CroppedProduct> cart;
    private String image;

    public CroppedCustomer(Customer customer){
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.cart = new ArrayList<>();
        this.image = "http://esc.volnc.ru/uploads/user_photo/6ce4341561-rapakov.jpg";
    }

}
