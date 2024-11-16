package com.ecommerce.ecommerceApp.controllers;

import com.ecommerce.ecommerceApp.model.Order;
import com.ecommerce.ecommerceApp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
}
