package com.portfolio.inventory_app.busines;

import com.portfolio.inventory_app.model.entities.Empleado;
import com.portfolio.inventory_app.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LiquidacionBusinessService {

    @Autowired EmpleadoService empleadoService;
    @Autowired TaxManager taxManager;

    public BigDecimal procesarSueldoMes(Long empleadoId) {
        Empleado emp = empleadoService.encontrarPorId(empleadoId);
        // Aquí podrías agregar lógica extra, como validar si el empleado está de VACACIONES
        return taxManager.calcularSueldoNeto(emp.getInformacionLaboral());
    }
}
