package com.portfolio.inventory_app.model;

import com.portfolio.inventory_app.model.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Empleado extends Usuario {

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @JoinColumn(name = "cargo_id")
    private Puesto puesto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ingreso;

    @Temporal(TemporalType.TIMESTAMP)
    private Date egreso;

    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;

    @Enumerated(EnumType.STRING)
    private Jornada jornada;

    @Enumerated(EnumType.STRING)
    private Contrato contrato;

    @Enumerated(EnumType.STRING)
    private Disponibilidad disponibilidad;

    @Column(unique = true)
    private String legajo;

    private Double salarioBase;
    private Double comision;
    private String sucursal;
    private Double objetivoMensual;
    private String obraSocial;

    @Column(length = 22)
    private String cbu;

//    public boolean puedeVender() {
//        return Sector.VENTA_COMERCIAL.equals(this.cargo) &&
//                this.getEstado() &&
//                Disponibilidad.PRESENTE.equals(this.disponibilidad);
//    }
}
