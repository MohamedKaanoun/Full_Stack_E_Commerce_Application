package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.model.OrderItem;
import com.backend.EcommerceProjectBackEnd.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/oder-items")
public class OderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOderItem = orderItemService.saveOrderItem(orderItem);
        return ResponseEntity.ok(savedOderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> oderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(oderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOderItemById(@PathVariable Integer id) {
        return orderItemService.getOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOderItem(@PathVariable Integer id, @RequestBody OrderItem oderItem) {
        try {
            OrderItem updatedOderItem = orderItemService.updateOrderItem(id, oderItem);
            return ResponseEntity.ok(updatedOderItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOderItem(@PathVariable Integer id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
