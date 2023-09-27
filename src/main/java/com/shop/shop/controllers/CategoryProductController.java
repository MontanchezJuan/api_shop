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

import com.shop.shop.models.Category;
import com.shop.shop.models.CategoryProduct;
import com.shop.shop.models.Product;
import com.shop.shop.repositories.CategoryProductRepository;
import com.shop.shop.repositories.CategoryRepository;
import com.shop.shop.repositories.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/category-product")
public class CategoryProductController {

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<CategoryProduct> list() {
        return this.categoryProductRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("category/{category_id}/product/{product_id}")
    public CategoryProduct create(@PathVariable String category_id, @PathVariable String product_id) {
        Category currentCategory = this.categoryRepository.findById(category_id).orElse(null);
        Product currentProduct = this.productRepository.findById(product_id).orElse(null);
        if (currentCategory != null && currentProduct != null) {
            CategoryProduct newCategoryProduct = new CategoryProduct();
            newCategoryProduct.setCategory(currentCategory);
            newCategoryProduct.setProduct(currentProduct);
            return this.categoryProductRepository.save(newCategoryProduct);
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public CategoryProduct getOne(@PathVariable String id) {
        CategoryProduct categoryProduct = this.categoryProductRepository.findById(id).orElse(null);
        return categoryProduct;
    }

    @PutMapping("{id}/category/{category_id}/product/{product_id}")
    public CategoryProduct update(@PathVariable String id, @PathVariable String category_id,
            @PathVariable String product_id, @RequestBody CategoryProduct NewCategoryProduct) {
        Category currentCategory = this.categoryRepository.findById(category_id).orElse(null);
        Product currentProduct = this.productRepository.findById(product_id).orElse(null);
        CategoryProduct currentCategoryProduct = this.categoryProductRepository.findById(id).orElse(null);
        if (currentCategory != null && currentProduct != null && currentCategoryProduct != null) {
            currentCategoryProduct.setCategory(currentCategory);
            currentCategoryProduct.setProduct(currentProduct);
            return this.categoryProductRepository.save(currentCategoryProduct);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        CategoryProduct categoryProduct = this.categoryProductRepository.findById(id).orElse(null);
        if (categoryProduct != null) {
            this.categoryProductRepository.delete(categoryProduct);
        }
    }
}
