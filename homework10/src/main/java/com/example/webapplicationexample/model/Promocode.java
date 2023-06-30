package com.example.webapplicationexample.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "promocodes")
public class Promocode {
    @Id
    String promocode;
    int percent;
}
