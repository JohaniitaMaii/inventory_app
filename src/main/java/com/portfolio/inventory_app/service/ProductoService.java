package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.Producto;
import com.portfolio.inventory_app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }

    // Guardar un producto con una regla de negocio simple
    public Producto save(Producto product) {
        // Ejemplo de lógica: Asegurar que el nombre esté en mayúsculas
        product.setNombre(product.getNombre().toUpperCase());
        return productoRepository.save(product);
    }

    // Buscar por ID
    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Eliminar
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}
