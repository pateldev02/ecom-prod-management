package com.ecommerce.ecommerceApp.controllers;

import com.ecommerce.ecommerceApp.model.Products;
import com.ecommerce.ecommerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // add multiple products
    @PostMapping
    public String addProducts(@RequestBody List<Products> products) {
        return productService.addProducts(products);
    }

    // Get list of products, optionally filtered by category
    @GetMapping
    public List<Products> getProducts(@RequestParam(required = false) Integer categoryId) {
        return productService.getProducts(categoryId);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Products getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // Update product by ID
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Products product) {
        return productService.updateProduct(id, product);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
