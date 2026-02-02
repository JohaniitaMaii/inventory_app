package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaId(Long categoriaId);
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

}
