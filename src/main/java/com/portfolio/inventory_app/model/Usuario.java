package com.portfolio.inventory_app.model;

import com.portfolio.inventory_app.model.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column( nullable = false, length = 100)
    private String nombre;

    private String domicilio;

    @Column (unique = true, nullable = false)
    private String email;

    private String telefono;

    @Column (unique = true, nullable = false)
    private String cuitDni;

    @Column(name = "estado")
    private boolean estado= true;

}
