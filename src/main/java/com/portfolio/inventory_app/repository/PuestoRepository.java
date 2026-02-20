package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.entities.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Long> {

    Optional<Puesto> findByNombreIgnoreCase(String nombre);

}
