package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.exception.BusinessLogicException;
import com.portfolio.inventory_app.model.Cliente;
import com.portfolio.inventory_app.model.enums.CategoriaFiscal;
import com.portfolio.inventory_app.model.enums.Comportamiento;
import com.portfolio.inventory_app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired private ClienteRepository clienteRepository;

    public Cliente validarClienteParaVenta(Long id) {
        if (id == null || id == 1L) {
            return getById(1L);
        }
        Cliente cliente = getById(id);
        if (cliente.getComportamiento() == Comportamiento.MOROSO ||
                cliente.getComportamiento() == Comportamiento.FRAUDULENTO) {
            throw new BusinessLogicException("Atención: El cliente tiene un historial de: " + cliente.getComportamiento());
        }
        verificarEstadoIntegral(cliente);
        return cliente;
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(()
                        -> new BusinessLogicException("Error: Cliente con ID " + id + " no encontrado en Base de Datos."));
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        clienteRepository.findByCuitDni(cliente.getCuitDni()).ifPresent(c -> {
            throw new BusinessLogicException("Ya existe un cliente registrado con el documento: " + cliente.getCuitDni());
        });
        validarDocumentacionSegunFiscalidad(cliente);
        return clienteRepository.save(cliente);
    }

    private void verificarEstadoIntegral(Cliente cliente) {
        if (!cliente.isEstado()) {
            throw new BusinessLogicException("Operación Cancelada: El cliente " + cliente.getNombre() + " está inactivo.");
        }
    }

    private void validarDocumentacionSegunFiscalidad(Cliente cliente) {
        if (cliente.getCategoriaFiscal() == CategoriaFiscal.RESPONSABLE_INSCRIPTO) {
            if (cliente.getCuitDni() == null || cliente.getCuitDni().length() != 11) {
                throw new BusinessLogicException("Para Responsable Inscripto se requiere un CUIT válido de 11 dígitos.");
            }
        }
    }

}
