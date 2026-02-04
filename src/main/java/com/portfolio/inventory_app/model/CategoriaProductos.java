package com.portfolio.inventory_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name= "categorias")
@Data
public class CategoriaProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debe ingresar una Categoria")
    @Column(unique = true)
    private String nombre;

    private Boolean activo = true;

}
