package com.portfolio.inventory_app.busines;

import com.portfolio.inventory_app.exception.FiscalConfigException;
import com.portfolio.inventory_app.model.domain.InformacionLaboral;
import com.portfolio.inventory_app.model.entities.Empleado;
import com.portfolio.inventory_app.model.enums.CategoriaFiscal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxManager {

    public BigDecimal calcularNeto(Empleado empleado) {
        InformacionLaboral datos = empleado.getInformacionLaboral();

        // Verificamos si tiene datos fiscales asignados, si no, disparamos tu excepción
        if (datos == null || datos.getSueldoBasico() == null) {
            throw new FiscalConfigException("El empleado " + empleado.getNombre() + " no tiene configuración fiscal completa.");
        }

        BigDecimal bruto = datos.getSueldoBasico();
        BigDecimal retencion = calcularRetencion(datos.getCategoriaFiscal());

        return bruto.subtract(bruto.multiply(retencion));
    }

    private BigDecimal calcularRetencion(CategoriaFiscal categoria) {
        // Lógica de negocio avanzada: mapeamos el Enum a una tasa [cite: 2026-02-10]
        return switch (categoria) {
            case RESPONSABLE_INSCRIPTO -> new BigDecimal("0.21"); // 21%
            case MONOTRIBUTO -> new BigDecimal("0.05"); // 5% fijo ejemplo
            case CONSUMIDOR_FINAL -> new BigDecimal("0.00");
            default -> throw new FiscalConfigException("Categoría fiscal no contemplada para retenciones.");
        };
    }
}
