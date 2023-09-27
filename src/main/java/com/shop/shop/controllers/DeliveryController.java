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

import com.shop.shop.models.Delivery;
import com.shop.shop.repositories.DeliveryRepository;

@CrossOrigin
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @GetMapping("")
    public List<Delivery> list() {
        return this.deliveryRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Delivery create(@RequestBody Delivery newDelivery) {
        return this.deliveryRepository.save(newDelivery);
    }

    @GetMapping("{id}")
    public Delivery getOne(@PathVariable String id) {
        Delivery delivery = this.deliveryRepository.findById(id).orElse(null);
        return delivery;
    }

    @PutMapping("{id}")
    public Delivery update(@PathVariable String id, @RequestBody Delivery newDelivery) {
        Delivery currentDelivery = this.deliveryRepository.findById(id).orElse(null);
        if (currentDelivery != null) {
            currentDelivery.setName(newDelivery.getName());
            return this.deliveryRepository.save(currentDelivery);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Delivery delivery = this.deliveryRepository.findById(id).orElse(null);
        if (delivery != null) {
            this.deliveryRepository.delete(delivery);
        }
    }
}
