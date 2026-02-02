package com.portfolio.inventory_app.service;

import com.portfolio.inventory_app.model.*;
import com.portfolio.inventory_app.repository.ClienteRepository;
import com.portfolio.inventory_app.repository.EmpleadoRepository;
import com.portfolio.inventory_app.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    @Autowired private VentaRepository ventaRepositorio;
    @Autowired private ProductoService productoService;
    @Autowired private EmpleadoRepository empleadoRepository;
    @Autowired private ClienteRepository clienteRepository;

    @Transactional
    public Venta save(Venta venta) {
        BigDecimal totalVenta = BigDecimal.ZERO;

        Empleado empleado = empleadoRepository.findById(venta.getEmpleado().getId())
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));
        if (!empleado.puedeVender()) {
            throw new RuntimeException("El empleado no tiene los permisos o la disponibilidad para vender.");
        }
        Cliente cliente = clienteRepository.findById(venta.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        venta.setEmpleado(empleado);
        venta.setCliente(cliente);
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto p = productoService.getById(detalle.getProducto().getId());
            if (p.getStock() < detalle.getCantidad()) {
                throw  new RuntimeException("Stock insuficiente para : " + p.getNombre());
            }

            p.setStock(p.getStock() - detalle.getCantidad());
            productoService.save(p);

            detalle.setVenta(venta);
            detalle.setPrecioUnitario(p.getPrecio());

            BigDecimal subtotal = p.getPrecio().multiply(new BigDecimal(detalle.getCantidad()));
            totalVenta = totalVenta.add(subtotal);
        }
        venta.setTotal(totalVenta);
        return ventaRepositorio.save(venta);
    }

    public List<Venta> listAll() {
        return ventaRepositorio.findAll();
    }

    public Venta getById(Long id) {
        return ventaRepositorio.findById(id).orElse(null);
    }

    public List<Venta> listarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepositorio.findByFechaBetween(inicio, fin);
    }

    public List<Venta> listarPorEmpleadoId(Long empleadoId) {
        return ventaRepositorio.findByEmpleadoId(empleadoId);
    }


    public List<Venta> listarPorCliente(Long clienteId) {
        return ventaRepositorio.findByClienteId(clienteId);
    }
}
