package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByEmpleado_Id(Long id);
    List<Venta> findByCliente_Id(Long id);
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin")
    BigDecimal sumTotalVentasPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}
