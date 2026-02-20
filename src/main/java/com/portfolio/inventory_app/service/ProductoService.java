package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.exception.StockInsuficienteException;
import com.portfolio.inventory_app.model.entities.Producto;
import com.portfolio.inventory_app.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listActivos() {
        return productoRepository.findAll().stream()
                .filter(Producto::isActivo)
                .toList();
    }

    public List<Producto> listInactivos() {
        return productoRepository.findAll().stream()
                .filter(p -> !p.isActivo())
                .toList();
    }

    public List<Producto> listAll() {
        return productoRepository.findAll();
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en Base de datos"));
    }

    public Producto buscarPorCodigoBarras(String codigo) {
        return productoRepository.findByCodigoBarras(codigo)
                .orElseThrow(() -> new RuntimeException("Código de barras no reconocido: " + codigo));
    }

    @Transactional
    public Producto save(Producto p) {
        if (p.getPrecioCosto() != null && p.getMargenGanancia() != null) {
            p.setPrecio(calcularPrecioVenta(p));
        }
        return productoRepository.save(p);
    }

    private BigDecimal calcularPrecioVenta(Producto p) {
        BigDecimal costo = (p.getPrecioCosto() != null) ? p.getPrecioCosto() : BigDecimal.ZERO;
        BigDecimal margen = (p.getMargenGanancia() != null) ? p.getMargenGanancia() : BigDecimal.ZERO;
        BigDecimal impuesto = (p.getIva() != null) ? p.getIva() : BigDecimal.ZERO;
        BigDecimal factorMargen = BigDecimal.ONE.add(margen.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
        BigDecimal factorIva = BigDecimal.ONE.add(impuesto.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
        return costo.multiply(factorMargen).multiply(factorIva)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Transactional
    public Producto update(Long id, Producto detalles) {
        Producto p = getById(id);
        p.setNombre(detalles.getNombre().toUpperCase().trim());
        p.setDescripcion(detalles.getDescripcion());
        p.setMarca(detalles.getMarca());
        p.setCategoriaProductos(detalles.getCategoriaProductos());
        p.setPrecio(detalles.getPrecio());
        p.setStockActual(detalles.getStockActual());
        p.setStockMinimo(detalles.getStockMinimo());
        p.setActivo(detalles.isActivo());
        p.setCodigoBarras(detalles.getCodigoBarras());
        return productoRepository.save(p);
    }

    @Transactional
    public void delete(Long id) {
        Producto p = getById(id);
        p.setActivo(false);
        productoRepository.save(p);
    }

    @Transactional
    public void actualizarStock(Long id, Integer cantidad, boolean esIncremento) {
        Producto p = getById(id);
        int nuevoStock;

        if (esIncremento) {
            nuevoStock = p.getStockActual() + cantidad;
        } else {
            nuevoStock = p.getStockActual() - cantidad;
            if (nuevoStock < 0) {
                throw new StockInsuficienteException("ADVERTENCIA: Venta con stock insuficiente para Producto: " + p.getNombre());
            }
        }

        p.setStockActual(nuevoStock);
        productoRepository.save(p);
    }

    public void actualizarPrecio(Long id, BigDecimal nuevoPrecio) {
        if (nuevoPrecio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El precio debe ser mayor a cero.");
        }
        Producto p = getById(id);
        p.setPrecio(nuevoPrecio);
        productoRepository.save(p);
    }

    public List<Producto> obtenerAlertasStock() {
        return productoRepository.findAll().stream()
                .filter(p -> p.isActivo() && p.getStockActual() <= p.getStockMinimo())
                .toList();
    }

    @Transactional
    public void actualizarPrecioPorCosto(Long id, BigDecimal nuevoCosto, BigDecimal nuevoMargen, BigDecimal ivaPorcentaje) {
        Producto p = getById(id);
        p.setPrecioCosto(nuevoCosto);
        if (nuevoMargen != null) p.setMargenGanancia(nuevoMargen);
        if (ivaPorcentaje != null) p.setIva(ivaPorcentaje);
        p.setPrecio(calcularPrecioVenta(p));
        productoRepository.save(p);
    }

}

