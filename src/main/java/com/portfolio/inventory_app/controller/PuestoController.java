package com.portfolio.inventory_app.controller;

import com.portfolio.inventory_app.model.entities.Puesto;
import com.portfolio.inventory_app.service.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puestos")
@CrossOrigin(value = "http://localhost:4200")
public class PuestoController {

    @Autowired
    private PuestoService puestoService;

    @GetMapping
    public List<Puesto> listarTodos() {
        return puestoService.listAll();
    }
}
