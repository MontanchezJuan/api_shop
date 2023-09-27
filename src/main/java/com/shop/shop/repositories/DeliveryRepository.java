package com.shop.shop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.shop.models.Delivery;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {

}
