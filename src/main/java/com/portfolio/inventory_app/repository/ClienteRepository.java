package com.portfolio.inventory_app.repository;


import com.portfolio.inventory_app.model.entities.Cliente;
import com.portfolio.inventory_app.model.enums.CategoriaFiscal;
import com.portfolio.inventory_app.model.enums.Comportamiento;
import com.portfolio.inventory_app.model.enums.Origen;
import com.portfolio.inventory_app.model.enums.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCuitDni(String cuitDni);
    List<Cliente> findByNombreIgnoreCaseContaining(String nombre);
    List<Cliente> findByTipoCliente(TipoCliente tipoCliente);
    List<Cliente> findByComportamiento(Comportamiento comportamiento);
    List<Cliente> findByCategoriaFiscal(CategoriaFiscal categoriaFiscal);
    List<Cliente> findByOrigen(Origen origen);
    List<Cliente> findByPuntuacion(Integer puntuacion);
    @Query("SELECT c FROM Cliente c WHERE c.puntuacion >= :umbral ORDER BY c.puntuacion DESC")
    List<Cliente> findTopPuntuacion(@Param("umbral") Integer umbral);
    List<Cliente> findByPreferencia(String preferencia);

}
