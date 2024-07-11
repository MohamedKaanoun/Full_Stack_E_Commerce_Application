package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.model.OrderItem;
import com.backend.EcommerceProjectBackEnd.repository.OrderItemRepository;
import com.backend.EcommerceProjectBackEnd.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImplementation implements OrderItemService {


    final private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem saveOrderItem(OrderItem oderItem) {
        return orderItemRepository.save(oderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Integer id) {
        return orderItemRepository.findById(id);
    }


    @Override
    public OrderItem updateOrderItem(Integer id, OrderItem oderItem) {
        Optional<OrderItem> optionalOderItem = orderItemRepository.findById(id);
        if (optionalOderItem.isPresent()) {
            OrderItem existingOderItem = optionalOderItem.get();
            existingOderItem.setOrder(oderItem.getOrder());
            existingOderItem.setProduct(oderItem.getProduct());
            existingOderItem.setSize(oderItem.getSize());
            existingOderItem.setQuantity(oderItem.getQuantity());
            existingOderItem.setPrice(oderItem.getPrice());
            existingOderItem.setDiscountedPrice(oderItem.getDiscountedPrice());
            existingOderItem.setUserId(oderItem.getUserId());
            existingOderItem.setDeliveryDate(oderItem.getDeliveryDate());
            return orderItemRepository.save(existingOderItem);
        } else {
            throw new RuntimeException("OderItem not found with id " + id);
        }
    }

    @Override
    public void deleteOrderItem(Integer id) {
        orderItemRepository.deleteById(id);
    }
}
