package com.shop.shop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.shop.models.Client;

public interface ClientRepository extends MongoRepository<Client, String>{
    
}
