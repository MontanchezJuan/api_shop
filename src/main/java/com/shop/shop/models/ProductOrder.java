package com.shop.shop.models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class ProductOrder {
    @Id
    private String _id;
    @DBRef
    private Product product;
    @DBRef
    private Order order;

    public ProductOrder() {   
    }

    public String get_id() {
        return _id;
    }
    public Product geProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}

