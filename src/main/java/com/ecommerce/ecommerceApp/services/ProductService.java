package com.ecommerce.ecommerceApp.services;

import com.ecommerce.ecommerceApp.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Bulk insert method for adding multiple products
    public String addProducts(List<Products> products) {
        String sql = "INSERT INTO products (name, description, price, category_id) VALUES (?, ?, ?, ?)";
        try {
            for (Products product : products) {
                jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getCategoryId());
            }
            return "Products added successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to add products: " + e.getMessage());
        }
    }

    // Update product by ID
    public String updateProduct(int id, Products product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, category_id = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getCategoryId(), id);
        return rows > 0 ? "Product updated successfully" : "Failed to update product";
    }

    // Get products by category or all products if category is null
    public List<Products> getProducts(Integer categoryId) {
        String sql = (categoryId == null) ? "SELECT * FROM products" : "SELECT * FROM products WHERE category_id = ?";
        return (categoryId == null)
                ? jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Products.class))
                : jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Products.class), categoryId);
    }

    // Get product by ID
    public Products getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Products.class), id);
    }

    // Delete product by ID
    public String deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0 ? "Product deleted successfully" : "Failed to delete product";
    }
}
