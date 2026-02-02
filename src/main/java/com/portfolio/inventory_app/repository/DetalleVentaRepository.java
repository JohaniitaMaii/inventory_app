package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository  extends JpaRepository<DetalleVenta, Long> {
}
