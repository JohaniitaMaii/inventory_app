package com.portfolio.inventory_app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

//    @Autowired
//    private CategoriaRepository categoriaRepository;

//    @GetMapping
//    public List<CategoriaProductos> gettAll(){
//        return categoriaRepository.findByActivoTrue();
//    }
//
//    @PostMapping
//    public ResponseEntity<CategoriaProductos> create(@RequestBody CategoriaProductos categoria){
//        return new ResponseEntity<>(categoriaRepository.save(categoria), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        CategoriaProductos c = categoriaRepository.findById(id).orElse(null);
//        if(c != null){
//            c.setActivo(false);
//            categoriaRepository.save(c);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


}
