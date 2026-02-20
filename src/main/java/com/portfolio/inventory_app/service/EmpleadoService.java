package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.exception.BusinessLogicException;
import com.portfolio.inventory_app.exception.EmployeeStatusException;
import com.portfolio.inventory_app.model.entities.Empleado;
import com.portfolio.inventory_app.model.enums.Disponibilidad;
import com.portfolio.inventory_app.repository.EmpleadoRepository;
import com.portfolio.inventory_app.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired private EmpleadoRepository empleadoRepository;
    @Autowired private PuestoService puestoService;
    @Autowired private DataValidator validator;

    public List<Empleado> listAll() {
        return empleadoRepository.findAll();
    }

    public Empleado save(Empleado empleado) {
        empleadoRepository.findByCuitDni(empleado.getDni()).ifPresent(e -> {
            throw new BusinessLogicException("El empleado ya existe");
        });
        validator.validarEmail(empleado.getEmail());
        return empleadoRepository.save(empleado);
    }

    public Empleado validarVendedor(Long id) {
        Empleado e = encontrarPorId(id);
        verificarEstadoIntegral(e);
        puestoService.validarCapacidadDeVenta(e.getPuesto());
        return e;
    }

    public Empleado validarGestorInventario(Long id) {
        Empleado e = encontrarPorId(id);
        verificarEstadoIntegral(e);
        puestoService.validarCapacidadGestionInventario(e.getPuesto());
        return e;
    }

    public Empleado validarGestorEmpleados(Long id){
        Empleado e = encontrarPorId(id);
        verificarEstadoIntegral(e);
        puestoService.validarCapacidadGestionEmpleados(e.getPuesto());
        return e;
    }

    public Empleado validarReportesSensibles(Long id){
        Empleado e = encontrarPorId(id);
        verificarEstadoIntegral(e);
        puestoService.validarCapacidadReportesSensibles(e.getPuesto());
        return e;
    }

    public Empleado validarGestorClientes(Long id){
        Empleado e = encontrarPorId(id);
        verificarEstadoIntegral(e);
        puestoService.validarCapacidadGestionClientes(e.getPuesto());
        return e;
    }

    public Empleado validarRealizarMantenimiento(Long id){
        Empleado e = encontrarPorId(id);
        verificarEstadoIntegral(e);
        puestoService.validarCapacidadRealizarMantenimiento(e.getPuesto());
        return e;
    }

    public Empleado validarConfigSistema(Long id){
        Empleado e = encontrarPorId(id);
        verificarEstadoIntegral(e);
        puestoService.validarCapacidadConfigSistema(e.getPuesto());
        return e;
    }

    private Empleado encontrarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("Error: Empleado con ID " + id + " no existe en la base de datos."));
    }

    private void verificarEstadoIntegral(Empleado empleado) {
        if (!empleado.isEstado()) throw new RuntimeException("Empleado dado de baja.");
        if (empleado.getDisponibilidad() != Disponibilidad.PRESENTE) {
            throw new EmployeeStatusException("Situación administrativa: " + empleado.getDisponibilidad());
        }
        // verificarHorarioLaboral(empleado);
    }

}
