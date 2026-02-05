package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByCodigoBarras(String codigoBarras);
    List<Producto> findByMarca(String marca);
    List<Producto> findByActivoTrue();
    List<Producto> findByActivoFalse();
    List<Producto> findByCategoriaProductos(Long categoriaId);
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    @Query("SELECT p FROM Producto p WHERE p.stock <= :minimo")
    List<Producto> findProductosBajoStock(@Param("minimo") Integer minimo);
    boolean existsByCodigoBarras(String codigoBarras);
}
