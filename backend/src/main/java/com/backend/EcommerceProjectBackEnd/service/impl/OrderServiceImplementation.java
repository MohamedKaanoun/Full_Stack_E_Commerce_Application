package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.exception.OrderException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.*;
import com.backend.EcommerceProjectBackEnd.repository.*;
import com.backend.EcommerceProjectBackEnd.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    final private CartService cartService;
    final private AddressRepository addressRepository;
    final private OrderRepository orderRepository;
    final private UserRepository userRepository;
    final private OrderItemRepository orderItemRepository;
    @Override
    public Order createOrder(User user, Address shippingAddress) throws UserException {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAdresses().add(address);
        userRepository.save(user);
        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cart.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSize(cartItem.getSize());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUserId(cartItem.getUserId());
            orderItem.setDiscountedPrice(cartItem.getDiscountedPrice());

            orderItems.add(orderItemRepository.save(orderItem));
        }
        Order order  = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalPrice(cart.getTotalPrice());
        order.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        order.setDiscount(cart.getDiscount());
        order.setTotalItems(cart.getTotalItem());
        order.setShippingAddress(address);
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        for(OrderItem item : orderItems){
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    @Override
    public Order findOrderById(Integer id) throws OrderException {
        Optional<Order> opt = orderRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new OrderException("order with id : "+id+" not exist");
    }

    @Override
    public List<Order> usersOrderHistory(Integer userId) {

        return orderRepository.getUsersOrders(userId);
    }

    @Override
    public Order placedOrder(Integer orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setPaymentStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Integer orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Integer id) throws OrderException {
        Order order = findOrderById(id);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Integer id) throws OrderException {
        Order order = findOrderById(id);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order canceledOrder(Integer id) throws OrderException {
        Order order = findOrderById(id);
        order.setOrderStatus("CANCELED");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getALlOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Integer orderId) throws OrderException {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);
    }
}
