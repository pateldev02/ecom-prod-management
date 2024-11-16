package com.ecommerce.ecommerceApp.services;

import com.ecommerce.ecommerceApp.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to add a new order item
    public String addOrderItem(int orderId, OrderItem orderItem) {
        // Validate product ID
        String checkProductSql = "SELECT COUNT(*) FROM products WHERE id = ?";
        Integer productExists = jdbcTemplate.queryForObject(checkProductSql, Integer.class, orderItem.getProductId());

        if (productExists == null || productExists == 0) {
            throw new RuntimeException("Product ID " + orderItem.getProductId() + " does not exist.");
        }

        // SQL query to insert a new order item
        String sql = "INSERT INTO orderitems (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, orderId, orderItem.getProductId(), orderItem.getQuantity(), orderItem.getUnitPrice());
            return "Order item added successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to add order item: " + e.getMessage());
        }
    }

    // Method to retrieve all order items for a specific order
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        String sql = "SELECT * FROM orderitems WHERE order_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderItem.class), orderId);
    }

    // Method to update an existing order item by item ID
    public String updateOrderItem(int itemId, OrderItem orderItem) {
        // Validate product ID
        String checkProductSql = "SELECT COUNT(*) FROM products WHERE id = ?";
        Integer productExists = jdbcTemplate.queryForObject(checkProductSql, Integer.class, orderItem.getProductId());

        if (productExists == null || productExists == 0) {
            throw new RuntimeException("Product ID " + orderItem.getProductId() + " does not exist.");
        }

        String sql = "UPDATE orderitems SET product_id = ?, quantity = ?, unit_price = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, orderItem.getProductId(), orderItem.getQuantity(), orderItem.getUnitPrice(), itemId);
        return rows > 0 ? "Order item updated successfully" : "Failed to update order item";
    }

    // Method to delete an order item by item ID
    public String deleteOrderItem(int itemId) {
        String sql = "DELETE FROM orderitems WHERE id = ?";
        int rows = jdbcTemplate.update(sql, itemId);
        return rows > 0 ? "Order item deleted successfully" : "Failed to delete order item";
    }
}
