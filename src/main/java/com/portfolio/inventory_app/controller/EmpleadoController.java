package com.portfolio.inventory_app.controller;

import com.portfolio.inventory_app.model.Empleado;
import com.portfolio.inventory_app.repository.EmpleadoRepository;
import com.portfolio.inventory_app.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(value = "http://localhost:4200")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listarTodos() {
        return empleadoService.listAll();
    }

    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado) {
        return empleadoService.save(empleado);
    }
}
