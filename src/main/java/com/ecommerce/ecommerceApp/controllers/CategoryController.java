package com.ecommerce.ecommerceApp.controllers;

import com.ecommerce.ecommerceApp.model.Categories;
import com.ecommerce.ecommerceApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // add multiple categories
    @PostMapping
    public String addCategories(@RequestBody List<Categories> categories) {
        return categoryService.addCategories(categories);
    }

    // Get list of all categories
    @GetMapping
    public List<Categories> getCategories() {
        return categoryService.getCategories();
    }

    // Update category by id
    @PutMapping("/{id}")
    public String updateCategory(@PathVariable int id, @RequestBody Categories category) {
        return categoryService.updateCategory(id, category);
    }

}
