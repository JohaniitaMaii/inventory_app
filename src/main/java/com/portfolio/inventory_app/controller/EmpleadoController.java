package com.portfolio.inventory_app.controller;

import com.portfolio.inventory_app.model.Empleado;
import com.portfolio.inventory_app.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @PostMapping
    public ResponseEntity<Empleado> save(@RequestBody Empleado empleado) {
        return new ResponseEntity<>(empleadoRepository.save(empleado), HttpStatus.OK);
    }

    @GetMapping
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }
}
