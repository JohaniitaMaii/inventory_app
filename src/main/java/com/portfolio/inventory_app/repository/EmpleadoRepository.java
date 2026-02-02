package com.portfolio.inventory_app.repository;


import com.portfolio.inventory_app.model.Empleado;
import com.portfolio.inventory_app.model.enums.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByCargo(Sector cargo);
}
