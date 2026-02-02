package com.portfolio.inventory_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "puesto")
@Data
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
