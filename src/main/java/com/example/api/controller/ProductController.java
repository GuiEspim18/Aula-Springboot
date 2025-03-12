package com.example.api.controller;

import com.example.api.model.Product;
import com.example.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getOne(id));
    }

    @PostMapping
    public void create(@RequestBody Product product) {
        productService.create(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product productUpdated) {
        Optional<Product> exists = productService.getOne(id);

        if (exists.isPresent()) {
            Product product = exists.get();

            product.setName(productUpdated.getName());
            product.setPrice(productUpdated.getPrice());

            Product produtoSalvo = productService.create(product);
            return ResponseEntity.ok(produtoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
