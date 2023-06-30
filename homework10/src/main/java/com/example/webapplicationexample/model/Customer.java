package com.example.webapplicationexample.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Покупатель
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
}
