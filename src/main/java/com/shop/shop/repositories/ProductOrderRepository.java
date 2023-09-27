package com.shop.shop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.shop.models.ProductOrder;

public interface ProductOrderRepository extends MongoRepository<ProductOrder, String> {

}
