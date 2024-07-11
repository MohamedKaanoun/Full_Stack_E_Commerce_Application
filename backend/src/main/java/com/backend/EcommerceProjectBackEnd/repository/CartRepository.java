package com.backend.EcommerceProjectBackEnd.repository;

import com.backend.EcommerceProjectBackEnd.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart , Integer> {
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    public Cart findByUserId(@Param("userId") Integer userId);
}
