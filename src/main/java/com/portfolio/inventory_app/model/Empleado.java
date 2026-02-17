package com.portfolio.inventory_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.portfolio.inventory_app.model.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Empleado extends Usuario {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "puesto_id")
    @JsonIgnoreProperties("empleados")
    private Puesto puesto;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso")
    private Date ingreso;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_egreso")
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

    @Column(name = "salario_base")
    private BigDecimal salarioBase;
    private BigDecimal comision;
    private String sucursal;

    @Column(name = "objetivo_mensual")
    private BigDecimal objetivoMensual;

    @Column(name = "obra_social")
    private String obraSocial;

    @Column(unique= true,length = 22)
    private String cbu;

}
