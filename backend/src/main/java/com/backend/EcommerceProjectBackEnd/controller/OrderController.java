package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.OrderException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Address;
import com.backend.EcommerceProjectBackEnd.model.Order;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.service.OrderService;
import com.backend.EcommerceProjectBackEnd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    final private OrderService orderService;
    final private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt) throws UserException {
        try {
            String sanitizedJwt = sanitizeToken(jwt);
            User user = userService.findUserProfileByJwt(sanitizedJwt);
            Order order = orderService.createOrder(user, shippingAddress);

            logger.info("Order created successfully: {}", order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating order: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String sanitizeToken(String authorizationHeader) throws UserException {
        if (authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7).trim();
        }
        throw new UserException("Invalid Authorization header format");
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException {
        try {
            String sanitizedJwt = sanitizeToken(jwt);
            User user = userService.findUserProfileByJwt(sanitizedJwt);
            List<Order> orders = orderService.usersOrderHistory(user.getId());

            logger.info("Retrieved order history for user: {}", user.getId());
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving order history: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id") Integer id,
                                               @RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        try {
            String sanitizedJwt = sanitizeToken(jwt);
            User user = userService.findUserProfileByJwt(sanitizedJwt);
            Order order = orderService.findOrderById(id);

            logger.info("Order found: {}", order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error finding order: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
