package com.portfolio.inventory_app.model;

import com.portfolio.inventory_app.model.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private String nombre;
    private String domicilio;

    @Column (unique = true, nullable = false)
    private String email;

    private Long telefono;

    @Column (unique = true, nullable = false)
    private Long cuit_dni;

    private Boolean estado;



}
