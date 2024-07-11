package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem oderItem);
    List<OrderItem> getAllOrderItems();
    Optional<OrderItem> getOrderItemById(Integer id);
    OrderItem updateOrderItem(Integer id, OrderItem orderItem);
    void deleteOrderItem(Integer id);
}
