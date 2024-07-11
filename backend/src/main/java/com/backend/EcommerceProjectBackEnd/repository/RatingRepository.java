package com.backend.EcommerceProjectBackEnd.repository;

import com.backend.EcommerceProjectBackEnd.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating , Integer> {
    @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
    List<Rating> getALlProductsRating(@Param("productId") Integer productId);
}
