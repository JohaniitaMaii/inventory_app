package com.portfolio.inventory_app.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( name = "sector")
@Data
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
}
