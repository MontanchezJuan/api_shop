package com.shop.shop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.shop.models.CategoryProduct;

public interface CategoryProductRepository extends MongoRepository<CategoryProduct, String> {

}
