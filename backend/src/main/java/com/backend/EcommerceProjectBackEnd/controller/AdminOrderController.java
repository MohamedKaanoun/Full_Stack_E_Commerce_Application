package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.OrderException;
import com.backend.EcommerceProjectBackEnd.model.Order;
import com.backend.EcommerceProjectBackEnd.response.ApiResponse;
import com.backend.EcommerceProjectBackEnd.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    final private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getALlOrdersHandler(){
        List<Order> orders = orderService.getALlOrders();
        return new ResponseEntity<List<Order>>(orders , HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<Order> confirmOrderHandler(@PathVariable Integer orderId
            , @RequestHeader("Authorization") String jwt) throws OrderException{
    Order order  = orderService.confirmedOrder(orderId);

    return new ResponseEntity<>(order , HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shipOrderHandler(@PathVariable Integer orderId
            , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order  = orderService.shippedOrder(orderId);
         return new ResponseEntity<>(order , HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliverOrderHandler(@PathVariable Integer orderId
            , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order  = orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order , HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable Integer orderId
            , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order  = orderService.canceledOrder(orderId);
        return new ResponseEntity<>(order , HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Integer orderId
            , @RequestHeader("Authorization") String jwt) throws OrderException {
        orderService.deleteOrder(orderId);

        com.backend.EcommerceProjectBackEnd.response.ApiResponse res = new ApiResponse();
        res.setMessage("Order deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res , HttpStatus.OK);
    }
}
