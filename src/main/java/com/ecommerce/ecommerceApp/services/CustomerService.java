package com.ecommerce.ecommerceApp.services;

import com.ecommerce.ecommerceApp.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Add a new customer
    public String addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        int rows = jdbcTemplate.update(sql, customer.getName(), customer.getEmail());
        return rows > 0 ? "Customer added successfully" : "Failed to add customer";
    }

    // Retrieve all customers
    public List<Customer> getCustomers() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    // Update an existing customer by ID
    public String updateCustomer(int id, Customer customer) {
        String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), id);
        return rows > 0 ? "Customer updated successfully" : "Failed to update customer";
    }

    // Delete a customer by ID
    public String deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0 ? "Customer deleted successfully" : "Failed to delete customer";
    }

    // Retrieve a customer by ID
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), id);
    }
}
