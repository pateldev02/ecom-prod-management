package com.ecommerce.ecommerceApp.controllers;

import com.ecommerce.ecommerceApp.model.Customer;
import com.ecommerce.ecommerceApp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // add a new customer
    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // retrieve all customers
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    // update a customer by ID
    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    // delete a customer by ID
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }

    // get a customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }
}
