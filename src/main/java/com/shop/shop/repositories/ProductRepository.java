package com.shop.shop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.shop.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
