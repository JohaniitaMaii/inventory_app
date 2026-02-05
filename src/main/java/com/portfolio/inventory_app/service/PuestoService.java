package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.Puesto;
import com.portfolio.inventory_app.repository.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuestoService {

    @Autowired private PuestoRepository puestoRepository;

    public void validarVendedor(Puesto puesto) {
        if (puesto == null || !"VENDEDOR".equalsIgnoreCase(puesto.getNombre())) {
            throw new RuntimeException("El puesto no es valido para realizar una venta");
        }
        if (puesto.getSector() == null ) {
            throw new RuntimeException("El puesto no pertenece al Sector Comercial/Ventas");
        }
    }
}
