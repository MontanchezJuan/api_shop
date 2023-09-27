package com.shop.shop.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.shop.shop.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    
}
