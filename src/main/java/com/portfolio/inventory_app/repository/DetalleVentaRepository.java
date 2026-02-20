package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository  extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByProductoId(Long productoId);
}
