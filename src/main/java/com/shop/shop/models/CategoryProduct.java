package com.shop.shop.models;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class CategoryProduct {
    @Id
    private String _id;

    @DBRef
    private Category category;

    // @DBRef
    // private Product product;

    public CategoryProduct() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // public Product getProduct() {
    // return product;
    // }

    // public void setProduct(Product product) {
    // this.product = product;
    // }
}
