package com.backend.EcommerceProjectBackEnd.repository;

import com.backend.EcommerceProjectBackEnd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR p.category.name = :category) " +
            "AND ((:minPrice IS NULL OR :maxPrice IS NULL) OR (p.discountPrice BETWEEN :minPrice AND :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.discountPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountPrice END DESC")
    List<Product> filterProducts(@Param("category") String category,
                                 @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice,
                                 @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort);
}
