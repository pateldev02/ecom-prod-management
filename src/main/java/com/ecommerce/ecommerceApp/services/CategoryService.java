package com.ecommerce.ecommerceApp.services;

import com.ecommerce.ecommerceApp.model.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Bulk insert method for adding multiple categories
    public String addCategories(List<Categories> categories) {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try {
            for (Categories category : categories) {
                jdbcTemplate.update(sql, category.getName());
            }
            return "Categories added successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to add categories: " + e.getMessage());
        }
    }

    // Get all categories
    public List<Categories> getCategories() {
        String sql = "SELECT * FROM categories";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categories.class));
    }

    // Update category by id
    public String updateCategory(int id, Categories category) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, category.getName(), id);
        return rows > 0 ? "Category updated successfully" : "Failed to update category";
    }


    public String addCategory(Categories category) {
        return null;
    }
}

