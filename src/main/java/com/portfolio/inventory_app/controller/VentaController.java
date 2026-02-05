package com.portfolio.inventory_app.controller;

import com.portfolio.inventory_app.model.Venta;
import com.portfolio.inventory_app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

//    @GetMapping
//    public ResponseEntity<List<Venta>> getAll() {
//        List<Venta> ventas = ventaService.listAll();
//        return new ResponseEntity<>(ventas, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> create(@PathVariable Long id) {
//        Venta v = ventaService.getById(id);
//        if (v != null) {
//            return new ResponseEntity<>(v, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody Venta venta) {
//        try {
//            Venta nuevaVenta = ventaService.save(venta);
//            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/empleado/{empleadoId}")
//    public ResponseEntity<List<Venta>> getByEmpleado(@PathVariable Long empleadoId) {
//        List<Venta> ventas = ventaService.listarPorEmpleadoId(empleadoId);
//        if (ventas.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(ventas, HttpStatus.OK);
//    }
//
//    @GetMapping("/cliente/{clienteId}")
//    public ResponseEntity<List<Venta>> getVentasByCliente(@PathVariable Long clienteId) {
//        List<Venta> ventas = ventaService.listarPorCliente(clienteId);
//        if (ventas.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(ventas, HttpStatus.OK);
//    }
//
//    @GetMapping("/fechas")
//    public ResponseEntity<List<Venta>> getVentaByFechas(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
//        List<Venta> ventas = ventaService.listarPorRangoFechas(inicio, fin);
//        return new ResponseEntity<>(ventas, HttpStatus.OK);
//    }
}
