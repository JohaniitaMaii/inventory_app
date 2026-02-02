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
        if (product.getNombre() != null) {
            product.setNombre(product.getNombre().toUpperCase());
        }
        if (product.getActivo() == null) {
            product.setActivo(true);
        }
        return productoRepository.save(product);
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto update(Long id, Producto productoDetalles) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
        p.setNombre(productoDetalles.getNombre());
        p.setDescripcion(productoDetalles.getDescripcion());
        p.setMarca(productoDetalles.getMarca());
        p.setCategoria(productoDetalles.getCategoria());
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
        return productoRepository.findByCategoriaId(id);
    }

    public List<Producto> findByMarca(String marca) {
        return productoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
