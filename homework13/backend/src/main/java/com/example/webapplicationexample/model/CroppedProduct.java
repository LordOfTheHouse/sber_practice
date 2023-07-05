package com.example.webapplicationexample.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CroppedProduct {

    private long id;
    private String name;
    private BigDecimal price;
    private int amount;
    private String image;
    public CroppedProduct(Product product){
        id = product.getId();
        name =product.getName();
        price = product.getPrice();
        amount = product.getAmount();
        image = "https://pluspng.com/img-png/react-logo-png-react-js-logo-history-design-history-and-evolution-5500x3094.png";
    }
}
