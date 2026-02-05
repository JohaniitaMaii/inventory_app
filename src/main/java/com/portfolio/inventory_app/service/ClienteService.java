package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.Cliente;
import com.portfolio.inventory_app.model.enums.CategoriaFiscal;
import com.portfolio.inventory_app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired private ClienteRepository clienteRepository;

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        if (clienteRepository.findByCuitDni(cliente.getCuitDni()).isPresent()) {
            throw new RuntimeException
                    ("Ya existe un cliente registrado con el documento: " + cliente.getCuitDni());
        }
        validarDocumentacionSegunFiscalidad(cliente);
        return clienteRepository.save(cliente);
    }

    private void validarDocumentacionSegunFiscalidad(Cliente cliente) {
        // Ejemplo: Si es Responsable Inscripto, el documento debe tener 11 dígitos (CUIT)
        if (cliente.getCategoriaFiscal() == CategoriaFiscal.RESPONSABLE_INSCRIPTO) {
            if (cliente.getCuitDni().length() != 11) {
                throw new RuntimeException("Para Responsable Inscripto se requiere un CUIT válido de 11 dígitos.");
            }
        }
    }

}
