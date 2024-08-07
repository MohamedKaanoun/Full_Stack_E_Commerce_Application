package com.backend.EcommerceProjectBackEnd.repository;

import com.backend.EcommerceProjectBackEnd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order , Integer> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    public List<Order> getUsersOrders(@Param("userId") Integer userId);
}
