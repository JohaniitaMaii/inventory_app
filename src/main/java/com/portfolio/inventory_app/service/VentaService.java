package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.*;
import com.portfolio.inventory_app.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ClienteService clienteService; // Lo cambiaremos a ClienteService pronto

    @Transactional
    public Venta registrarVenta(Venta venta) {

        Empleado vendedor = empleadoService.obtenerVendedor(venta.getEmpleado().getId());
        Cliente cliente = clienteService.getById(venta.getCliente().getId());

        venta.setEmpleado(vendedor);
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now());

        BigDecimal totalVenta = procesarLineasDeVenta(venta);
        venta.setTotal(totalVenta);

        return ventaRepository.save(venta);
    }

    private BigDecimal procesarLineasDeVenta(Venta venta) {
        BigDecimal acumulado = BigDecimal.ZERO;

        for (DetalleVenta detalle : venta.getDetalles()) {
            productoService.actualizarStock(detalle.getProducto().getId(), detalle.getCantidad(), false);
            Producto p = productoService.getById(detalle.getProducto().getId());
            detalle.setVenta(venta);
            detalle.setPrecioUnitario(p.getPrecio());
            BigDecimal subtotal = p.getPrecio().multiply(new BigDecimal(detalle.getCantidad()));
            acumulado = acumulado.add(subtotal);
        }
        return acumulado;
    }

}
