package com.portfolio.inventory_app.controller;


import com.portfolio.inventory_app.model.entities.Producto;
import com.portfolio.inventory_app.service.ProductoService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(value = "http://localhost:5173")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // 1. Listar todos los activos (para la vista de venta)
    @GetMapping
    public ResponseEntity<List<Producto>> listarActivos() {
        return ResponseEntity.ok(productoService.listActivos());
    }

    // 2. Buscar por ID o por Código de Barras (Lectura Rápida)
    // URL: /api/productos/buscar?codigo=123456
    @GetMapping("/buscar")
    public ResponseEntity<Producto> buscarProducto(@RequestParam String codigo) {
        try {
            // Intentamos buscar por código de barras primero
            return ResponseEntity.ok(productoService.buscarPorCodigoBarras(codigo));
        } catch (Exception e) {
            // Si no lo encuentra por barras, el Service podría intentar por ID
            return ResponseEntity.ok(productoService.getById(Long.parseLong(codigo)));
        }
    }

    // 3. Crear nuevo producto (con lógica de margen/costo)
    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
    }

    // 4. Actualización de precio express (Solo precio)
    @PatchMapping("/{id}/precio")
    public ResponseEntity<Void> actualizarPrecio(@PathVariable Long id, @RequestParam BigDecimal nuevoPrecio) {
        productoService.actualizarPrecio(id, nuevoPrecio);
        return ResponseEntity.noContent().build();
    }

    // 5. Alertas de Stock Bajo (Para tu panel de analíticas)
    @GetMapping("/alertas-stock")
    public ResponseEntity<List<Producto>> obtenerAlertas() {
        return ResponseEntity.ok(productoService.obtenerAlertasStock());
    }

    // 6. Borrado Lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
