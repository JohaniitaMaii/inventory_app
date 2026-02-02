package com.portfolio.inventory_app.controller;

import com.portfolio.inventory_app.model.Category;
import com.portfolio.inventory_app.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Category> gettAll(){
        return categoriaRepository.findByActivoTrue();
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category categoria){
        return new ResponseEntity<>(categoriaRepository.save(categoria), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Category c = categoriaRepository.findById(id).orElse(null);
        if(c != null){
            c.setActivo(false);
            categoriaRepository.save(c);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
