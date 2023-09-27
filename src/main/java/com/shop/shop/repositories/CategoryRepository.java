package com.shop.shop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.shop.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
