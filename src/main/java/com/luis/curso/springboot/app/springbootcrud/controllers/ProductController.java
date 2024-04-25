package com.luis.curso.springboot.app.springbootcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.luis.curso.springboot.app.springbootcrud.ProductValidation;
import com.luis.curso.springboot.app.springbootcrud.entities.Product;
import com.luis.curso.springboot.app.springbootcrud.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    // @Autowired
    // private ProductValidation validation;

    @GetMapping
    public List<Product> list() {
        System.out.println("productos");
        return productService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // BindingResult encapsula y contiene todas las validaciones y la informacion de
    // los mensajes de error que obtuvimos en el request cuando se envia el json
    // siempre se tiene que colocar al lado derecho del @RequestBody
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        // validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,
            @PathVariable Long id) {
        // validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Product> productOptional = productService.update(id, product);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> productOptional = productService.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
