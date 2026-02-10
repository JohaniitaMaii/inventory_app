package com.portfolio.inventory_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "puesto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @OneToMany(mappedBy = "puesto")
    private List<Empleado> empleados;

    // --- BLOQUE DE PERMISOS                           CAPACIDADES
    private boolean puedeVender;                // Realizar transacciones de venta
    private boolean puedeGestionarInventario;   // Alta/Baja/Modificación de productos
    private boolean puedeGestionarEmpleados;    // CRUD de empleados y puestos
    private boolean puedeVerReportesSensibles;  // Acceso a balances y ganancias
    private boolean puedeGestionarClientes;     // CRUD de base de datos de clientes
    private boolean puedeRealizarMantenimiento; // Acceso a módulos técnicos/edificios
    private boolean puedeConfigurarSistema;     // Modificar parámetros globales (SaaS)

    public Puesto(String nombre, Sector sector, boolean puedeVender,
                  boolean puedeGestionarInventario, boolean puedeGestionarEmpleados,
                  boolean puedeVerReportesSensibles, boolean puedeGestionarClientes,
                  boolean puedeRealizarMantenimiento, boolean puedeConfigurarSistema) {
        this.nombre = nombre;
        this.sector = sector;
        this.puedeVender = puedeVender;
        this.puedeGestionarInventario = puedeGestionarInventario;
        this.puedeGestionarEmpleados = puedeGestionarEmpleados;
        this.puedeVerReportesSensibles = puedeVerReportesSensibles;
        this.puedeGestionarClientes = puedeGestionarClientes;
        this.puedeRealizarMantenimiento = puedeRealizarMantenimiento;
        this.puedeConfigurarSistema = puedeConfigurarSistema;
    }
}
