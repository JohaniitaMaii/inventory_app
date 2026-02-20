package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.entities.CategoriaProductos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaProductos, Long> {

    List<CategoriaProductos> findByNombreContainingIgnoreCase(String nombre);
    List<CategoriaProductos> findByActivoTrue();
    List<CategoriaProductos> findByActivoFalse();

}
