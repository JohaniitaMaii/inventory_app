package com.portfolio.inventory_app.model;

import com.portfolio.inventory_app.model.enums.Comportamiento;
import com.portfolio.inventory_app.model.enums.Origen;
import com.portfolio.inventory_app.model.enums.TipoCliente;
import com.portfolio.inventory_app.model.enums.CategoriaFiscal;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Usuario {

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @Enumerated(EnumType.STRING)
    private Comportamiento comportamiento;

    @Enumerated(EnumType.STRING)
    private CategoriaFiscal categoriaFiscal;

    @Enumerated(EnumType.STRING)
    private Origen origen;

    private Integer puntuacion;
    private String preferencia;


}
