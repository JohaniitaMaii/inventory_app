package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.DetalleVenta;
import com.portfolio.inventory_app.model.Producto;
import com.portfolio.inventory_app.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DetalleVentaService {

    @Autowired private ProductoService productoService;

    public BigDecimal procesarLinea(DetalleVenta detalle, Venta venta) {
        Producto p = productoService.getById(detalle.getProducto().getId());
        detalle.setVenta(venta);
        detalle.setProducto(p);
        detalle.setPrecioUnitario(p.getPrecio());
        BigDecimal subtotal = p.getPrecio().multiply(new BigDecimal(detalle.getCantidad()));
        productoService.actualizarStock(p.getId(), detalle.getCantidad(), false);
        return subtotal;
    }
}
