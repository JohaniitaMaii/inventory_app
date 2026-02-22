package com.portfolio.inventory_app.busines;

import com.portfolio.inventory_app.exception.FiscalConfigException;
import com.portfolio.inventory_app.model.domain.InformacionLaboral;
import com.portfolio.inventory_app.model.entities.Empleado;
import com.portfolio.inventory_app.model.enums.CategoriaFiscal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxManager {

    private static final BigDecimal PORCENTAJE_SINDICATO = new BigDecimal("0.02");
    private static final BigDecimal PLUS_PRESENTISMO = new BigDecimal("0.10");

    public BigDecimal calcularSueldoNeto(InformacionLaboral info) {
        if (info == null || info.getSueldoBasico() == null) {
            throw new FiscalConfigException("Datos laborales incompletos para el cálculo."); //
        }

        BigDecimal sueldoBase = info.getSueldoBasico();

        // 1. Sumar Adicionales
        BigDecimal conAntiguedad = aplicarAntiguedad(sueldoBase, info.getPorcentajeAntiguedadPorAnio());
        BigDecimal conPresentismo = aplicarPresentismo(conAntiguedad, info.isAplicaPresentismo());

        // 2. Restar Deducciones (Sindicato e Impuestos)
        BigDecimal totalDeducciones = calcularRetencionFiscal(conPresentismo, info.getCategoriaFiscal());
        if (info.isAplicaSindicato()) {
            totalDeducciones = totalDeducciones.add(conPresentismo.multiply(PORCENTAJE_SINDICATO));
        }

        return conPresentismo.subtract(totalDeducciones).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal aplicarAntiguedad(BigDecimal base, BigDecimal porcentaje) {
        return base.add(base.multiply(porcentaje));
    }

    private BigDecimal aplicarPresentismo(BigDecimal total, boolean aplica) {
        return aplica ? total.add(total.multiply(PLUS_PRESENTISMO)) : total;
    }

    private BigDecimal calcularRetencionFiscal(BigDecimal base, CategoriaFiscal regimen) {
        return switch (regimen) {
            case RESPONSABLE_INSCRIPTO -> base.multiply(new BigDecimal("0.21"));
            case MONOTRIBUTO -> new BigDecimal("5000.00"); // Monto fijo ejemplo
            default -> BigDecimal.ZERO;
        };
    }

}
