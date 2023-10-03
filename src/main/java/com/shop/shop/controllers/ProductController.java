package com.shop.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shop.models.Product;
import com.shop.shop.repositories.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository; // Cambiado a productRepository

    @GetMapping("")
    public List<Product> index() {
        return this.productRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product store(@RequestBody Product newProduct) {
        // Lógica para crear un nuevo Product
        return this.productRepository.save(newProduct);
    }


    @GetMapping("{id}")
    public Product getOne(@PathVariable String id) {
        Product Product = this.productRepository
                .findById(id)
                .orElse(null);
        return Product;
    }

    @PutMapping("{id}")
    public Product update(@PathVariable String id, @RequestBody Product theNewProduct) {
        Product theActualProduct = this.productRepository.findById(id).orElse(null);
        if (theActualProduct != null) {
            theActualProduct.setName(theNewProduct.getName());
            theActualProduct.setPrice(theNewProduct.getPrice());
            // Actualizar el producto con los datos proporcionados
            return this.productRepository.save(theActualProduct);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Product theActualProduct = this.productRepository.findById(id).orElse(null);
        if (theActualProduct != null) {
            this.productRepository.delete(theActualProduct);
        }
    }
}
