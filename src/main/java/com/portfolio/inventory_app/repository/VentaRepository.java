package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByEmpleadoId(Long id);
    List<Venta> findByClienteId(Long id);
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
