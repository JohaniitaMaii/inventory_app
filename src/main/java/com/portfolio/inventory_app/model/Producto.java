package com.portfolio.inventory_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private Boolean activo= true;

    @DecimalMin(value = "0.1", message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private BigDecimal precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;

    @Column(name = "codigo_barras", unique = true)
    private String codigoBarras;

    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id", nullable = true)
    @JsonIgnoreProperties("productos")
    private Category categoria;

    @Column(name = "marca")
    private String marca;

}
