package com.shop.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Or;
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

import com.shop.shop.models.Client;
import com.shop.shop.models.Order;
import com.shop.shop.repositories.ClientRepository;
import com.shop.shop.repositories.OrderRepository;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderControllrer {
    @Autowired
    private OrderRepository orderRepository; // Cambiado a orderRepository
    @Autowired
    private ClientRepository clientRepository;

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
    public Order getOne(@PathVariable String id) {
        Order order = this.orderRepository
                .findById(id)
                .orElse(null);
        return order;
    }
    
    @PutMapping("{id}")
    public Order update(@PathVariable String id, @RequestBody Order theNewOrder) {
        Order theActualOrder = this.orderRepository.findById(id).orElse(null);
        if (theActualOrder != null) {
            theActualOrder.setDate(theNewOrder.getDate());
            theActualOrder.setPaymentMethod(theNewOrder.getPaymentMethod());
            theActualOrder.setAddress(theNewOrder.getAddress());
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

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("order/{order_id}/client/{client_id}")
    public Order matchOrderClient(@PathVariable String order_id, @PathVariable String client_id){
        Order theActualOrder = this.orderRepository.findById(order_id).orElse(null);
        Client theActualClient = this.clientRepository.findById(client_id).orElse(null);
        if(theActualOrder != null && theActualClient != null){
            theActualOrder.setClient(theActualClient);
            return this.orderRepository.save(theActualOrder);
        }
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{order_id}/client")
    public Order unMatchOrderRole(@PathVariable String order_id) {
        Order theActualOrder = this.orderRepository.findById(order_id)
                .orElse(null);
        if(theActualOrder!=null) {
            theActualOrder.setClient(null);
            return this.orderRepository.save(theActualOrder);
        }else{
            return null;
        }
    }
}


