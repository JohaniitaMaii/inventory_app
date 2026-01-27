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

    public List<Producto> listAll() {
        return productoRepository.findAll().stream()
                .filter(p -> Boolean.TRUE.equals(p.getActivo()))
                .toList();
    }

    public Producto save(Producto product) {
        product.setNombre(product.getNombre().toUpperCase());
        return productoRepository.save(product);
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Producto p = productoRepository.findById(id).orElse(null);
        if (p != null) {
            p.setActivo(false);
            productoRepository.save(p);
        }
    }

}
