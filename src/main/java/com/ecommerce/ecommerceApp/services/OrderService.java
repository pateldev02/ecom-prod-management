package com.ecommerce.ecommerceApp.services;

import com.ecommerce.ecommerceApp.model.Order;
import com.ecommerce.ecommerceApp.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String createOrder(Order order) {
        String insertOrderSql = "INSERT INTO orders (customer_id, total_amt) VALUES (?, ?) RETURNING id";
        String insertOrderItemSql = "INSERT INTO orderitems (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

        try {
            double totalAmount = 0.0;
            for (OrderItem item : order.getOrderItems()) {
                totalAmount += item.getUnitPrice() * item.getQuantity();
            }

            Integer orderId = jdbcTemplate.queryForObject(insertOrderSql, Integer.class, order.getCustomerId(), totalAmount);

            if (orderId == null) {
                throw new RuntimeException("Failed to create order");
            }

            for (OrderItem item : order.getOrderItems()) {
                jdbcTemplate.update(insertOrderItemSql, orderId, item.getProductId(), item.getQuantity(), item.getUnitPrice());
            }

            return "Order created successfully with ID: " + orderId;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create order: " + e.getMessage());
        }
    }
}
