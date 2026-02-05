package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.Empleado;
import com.portfolio.inventory_app.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired private EmpleadoRepository empleadoRepository;

    @Autowired private PuestoService puestoService;

    public Empleado obtenerVendedor(Long id) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        puestoService.validarVendedor(e.getPuesto());
        return e;
    }
}
