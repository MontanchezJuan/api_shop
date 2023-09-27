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
import com.shop.shop.repositories.CategoryRepository;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public List<Category> list() {
        return this.categoryRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Category create(@RequestBody Category newCategory) {
        return this.categoryRepository.save(newCategory);
    }

    @GetMapping("{id}")
    public Category getOne(@PathVariable String id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        return category;
    }

    @PutMapping("{id}")
    public Category update(@PathVariable String id, @RequestBody Category NewCategory) {
        Category currentCategory = this.categoryRepository.findById(id).orElse(null);
        if (currentCategory != null) {
            currentCategory.setName(NewCategory.getName());
            return this.categoryRepository.save(currentCategory);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        if (category != null) {
            this.categoryRepository.delete(category);
        }
    }
}
