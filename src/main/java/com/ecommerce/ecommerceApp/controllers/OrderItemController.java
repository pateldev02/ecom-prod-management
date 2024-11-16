package com.ecommerce.ecommerceApp.controllers;

import com.ecommerce.ecommerceApp.model.OrderItem;
import com.ecommerce.ecommerceApp.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // add a new order item
    @PostMapping("/{orderId}")
    public String addOrderItem(@PathVariable int orderId, @RequestBody OrderItem orderItem) {
        return orderItemService.addOrderItem(orderId, orderItem);
    }

    // get all order items for a specific order
    @GetMapping("/order/{orderId}")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable int orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    // update an existing order item by item ID
    @PutMapping("/{itemId}")
    public String updateOrderItem(@PathVariable int itemId, @RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(itemId, orderItem);
    }

    // delete an order item by item ID
    @DeleteMapping("/{itemId}")
    public String deleteOrderItem(@PathVariable int itemId) {
        return orderItemService.deleteOrderItem(itemId);
    }
}
