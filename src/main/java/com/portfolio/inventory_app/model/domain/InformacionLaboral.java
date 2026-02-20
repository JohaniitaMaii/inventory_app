package com.portfolio.inventory_app.model.domain;

import com.portfolio.inventory_app.model.enums.*;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class InformacionLaboral {

    private String cuitDni;

    @Enumerated(EnumType.STRING)
    private CategoriaFiscal categoriaFiscal;

    @Enumerated(EnumType.STRING)
    private Contrato contrato;

    @Enumerated(EnumType.STRING)
    private Jornada jornada;

    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;

    private BigDecimal sueldoBasico = BigDecimal.ZERO;
    private boolean aplicaPresentismo;
    private boolean aplicaSindicato;
    private BigDecimal porcentajeAntiguedadPorAnio = BigDecimal.ZERO;

}
