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

import com.shop.shop.models.Order;
import com.shop.shop.models.Product;
import com.shop.shop.models.ProductOrder;
import com.shop.shop.repositories.OrderRepository;
import com.shop.shop.repositories.ProductOrderRepository;
import com.shop.shop.repositories.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/product-order")
public class ProductOrderController {
    @Autowired
    private ProductOrderRepository productOrderRepository; // Cambiado a productOrderRepository

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("")
    public List<ProductOrder> index() {
        return this.productOrderRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("product/{product_id}/order/{order_id}")
    public ProductOrder store(@PathVariable String product_id, @PathVariable String order_id) {
        Product theActualProduct = this.productRepository.findById(product_id).orElse(null);
        Order theActualOrder = this.orderRepository.findById(order_id).orElse(null);
        if (theActualProduct != null && theActualOrder != null) {
            ProductOrder newProductOrder = new ProductOrder();
            newProductOrder.setProduct(theActualProduct);
            newProductOrder.setOrder(theActualOrder);
            return this.productOrderRepository.save(newProductOrder);
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public ProductOrder getOne(@PathVariable String id) {
        ProductOrder productOrder = this.productOrderRepository.findById(id).orElse(null);
        return productOrder;
    }

    @GetMapping("order_id/{order_id}")
    public List<Product> filterByOrder(@PathVariable String id) {
        ProductOrder productOrder = this.productOrderRepository.findById(id).orElse(null);
        return null;
    }
    

    @PutMapping("{id}/product/{product_id}/order/{order_id}")
    public ProductOrder update(@PathVariable String id, @PathVariable String product_id,
            @PathVariable String order_id, @RequestBody ProductOrder NewProductOrder) {
        Product theActualProduct = this.productRepository.findById(product_id).orElse(null);
        Order theActuaOrder = this.orderRepository.findById(order_id).orElse(null);
        ProductOrder theActualProductOrder = this.productOrderRepository.findById(id).orElse(null);
        if (theActualProduct != null && theActualProduct != null && theActualProductOrder != null) {
            theActualProductOrder.setProduct(theActualProduct);
            theActualProductOrder.setOrder(theActuaOrder);
            return this.productOrderRepository.save(theActualProductOrder);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        ProductOrder theActualProductOrder = this.productOrderRepository.findById(id).orElse(null);
        if (theActualProductOrder != null) {
            this.productOrderRepository.delete(theActualProductOrder);
        }
    }
}
