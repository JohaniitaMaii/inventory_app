package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.exception.UnauthorizedRoleException;
import com.portfolio.inventory_app.model.entities.Puesto;
import com.portfolio.inventory_app.repository.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    @Autowired private PuestoRepository puestoRepository;

    public List<Puesto> listAll() {
        return puestoRepository.findAll();
    }

    public void validarCapacidadDeVenta(Puesto puesto) {
        asegurarPuesto(puesto);
        if (!puesto.isPuedeVender()) {
            throw new UnauthorizedRoleException("Acceso Denegado: El puesto '" + puesto.getNombre() +
                    "' no cuenta con permisos de venta en el Sector " +
                    puesto.getSector().getNombre() + ".");
        }
    }

    public void validarCapacidadGestionInventario(Puesto puesto) {
        asegurarPuesto(puesto);
        if (!puesto.isPuedeGestionarInventario()) {
            throw new UnauthorizedRoleException("Acceso Denegado: El puesto '" + puesto.getNombre() +
                    "' no cuenta con permisos de Gestión de Inventario en el Sector " +
                    puesto.getSector().getNombre() + ".");
        }
    }

    public void validarCapacidadGestionEmpleados(Puesto puesto) {
        asegurarPuesto(puesto);
        if (!puesto.isPuedeGestionarEmpleados()) {
            throw new UnauthorizedRoleException("Acceso Denegado: El puesto '" + puesto.getNombre() +
                    "' no cuenta con permisos de Gestión de Empleados en el Sector " +
                    puesto.getSector().getNombre() + ".");
        }
    }

    public void validarCapacidadReportesSensibles(Puesto puesto) {
        asegurarPuesto(puesto);
        if (!puesto.isPuedeVerReportesSensibles()) {
            throw new UnauthorizedRoleException("Acceso Denegado: El puesto '" + puesto.getNombre() +
                    "' no cuenta con permisos para ver Reportes Sensibles en el Sector " +
                    puesto.getSector().getNombre() + ".");
        }
    }

    public void validarCapacidadGestionClientes(Puesto puesto) {
        asegurarPuesto(puesto);
        if (!puesto.isPuedeGestionarClientes()) {
            throw new UnauthorizedRoleException("Acceso Denegado: El puesto '" + puesto.getNombre() +
                    "' no cuenta con permisos para Gestionar Clientes en el Sector " +
                    puesto.getSector().getNombre() + ".");
        }
    }

    public void validarCapacidadRealizarMantenimiento(Puesto puesto) {
        asegurarPuesto(puesto);
        if (!puesto.isPuedeRealizarMantenimiento()) {
            throw new UnauthorizedRoleException("Acceso Denegado: El puesto '" + puesto.getNombre() +
                    "' no cuenta con permisos para Realizar Mantenimiento en el Sector " +
                    puesto.getSector().getNombre() + ".");
        }
    }

    public void validarCapacidadConfigSistema(Puesto puesto) {
        asegurarPuesto(puesto);
        if (!puesto.isPuedeConfigurarSistema()) {
            throw new UnauthorizedRoleException("Acceso Denegado: El puesto '" + puesto.getNombre() +
                    "' no cuenta con permisos para Configurar el Sistema en el Sector " +
                    puesto.getSector().getNombre() + ".");
        }
    }

    private void asegurarPuesto(Puesto puesto) {
        if (puesto == null) {
            throw new UnauthorizedRoleException("Error crítico: El empleado no posee un puesto configurado.");
        }
    }

}
