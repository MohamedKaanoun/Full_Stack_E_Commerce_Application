package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.exception.OrderException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Address;
import com.backend.EcommerceProjectBackEnd.model.Order;
import com.backend.EcommerceProjectBackEnd.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user , Address shippingAddress) throws UserException;

    public Order findOrderById(Integer id) throws OrderException;

    public List<Order> usersOrderHistory(Integer userId);

    public Order placedOrder(Integer orderId) throws OrderException;

    public Order confirmedOrder(Integer orderId) throws OrderException;

    public Order shippedOrder(Integer id) throws OrderException;

    public Order deliveredOrder(Integer id) throws OrderException;

    public Order canceledOrder(Integer id) throws OrderException;

    public List<Order> getALlOrders();

    public void deleteOrder(Integer orderId) throws OrderException;
}
