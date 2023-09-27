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
import com.shop.shop.models.Order;
import com.shop.shop.repositories.OrderRepository;

@CrossOrigin
@RestController
@RequestMapping("api/orders")
public class OrderControllrer {
        @Autowired
    private OrderRepository orderRepository; // Cambiado a orderRepository

    @GetMapping("")
    public List<Order> index() {
        return this.orderRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Order store(@RequestBody Order newOrder) {
        // Lógica para crear una nueva ordne
        return this.orderRepository.save(newOrder);
    }

    @GetMapping("{id}")
    public Order show(@PathVariable String id){
        Order order=this.orderRepository
                .findById(id)
                .orElse(null);
        return order;
    }

    @PutMapping("{id}")
    public Order update(@PathVariable String id, @RequestBody Order theNewOrder) {
        Order theActualOrder = this.orderRepository.findById(id).orElse(null);
        if (theActualOrder != null) {
            // Actualizar la orden con los datos proporcionados
            return this.orderRepository.save(theActualOrder);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Order theActualOrder = this.orderRepository.findById(id).orElse(null);
        if (theActualOrder != null) {
            this.orderRepository.delete(theActualOrder);
        }
    }
}
