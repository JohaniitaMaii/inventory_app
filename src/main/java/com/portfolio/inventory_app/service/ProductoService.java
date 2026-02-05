package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.Producto;
import com.portfolio.inventory_app.repository.ProductoRepository;
import jakarta.transaction.Transactional;
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
        if (product.getNombre() != null) {
            product.setNombre(product.getNombre().toUpperCase());
        }
        if (product.getActivo() == null) {
            product.setActivo(true);
        }
        return productoRepository.save(product);
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto update(Long id, Producto productoDetalles) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
        p.setNombre(productoDetalles.getNombre());
        p.setDescripcion(productoDetalles.getDescripcion());
        p.setMarca(productoDetalles.getMarca());
        p.setCategoriaProductos(productoDetalles.getCategoriaProductos());
        p.setPrecio(productoDetalles.getPrecio());
        p.setStock(productoDetalles.getStock());
        p.setActivo(productoDetalles.getActivo());
        p.setCodigoBarras(productoDetalles.getCodigoBarras());
        return productoRepository.save(p);
    }

    public void delete(Long id) {
        Producto p = productoRepository.findById(id).orElse(null);
        if (p != null) {
            p.setActivo(false);
            productoRepository.save(p);
        }
    }

    public List<Producto> findByCategoria(Long id) {
        return productoRepository.findByCategoriaProductos(id);
    }

    public List<Producto> findByMarca(String marca) {
        return productoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Transactional
    public void actualizarStock(Long id, Integer cantidad, boolean esIncremento) {

        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));

        int nuevoStock;
        if (esIncremento) {
            nuevoStock = p.getStock() + cantidad;
        } else {
            if (p.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente para: " + p.getNombre());
            }
            nuevoStock = p.getStock() - cantidad;
        }

        p.setStock(nuevoStock);
        productoRepository.save(p);
    }


}
