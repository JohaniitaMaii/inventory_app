package com.portfolio.inventory_app.controller;

import com.portfolio.inventory_app.model.Producto;
import com.portfolio.inventory_app.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAll() {
        return productoService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        Producto producto = productoService.getById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        Producto savedProducto = productoService.save(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
