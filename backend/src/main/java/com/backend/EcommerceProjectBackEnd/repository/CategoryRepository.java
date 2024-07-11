package com.backend.EcommerceProjectBackEnd.repository;

import com.backend.EcommerceProjectBackEnd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory.name = :parentCategoryName")
    Category findByNameAndParent(@Param("name") String name, @Param("parentCategoryName") String parentCategoryName);
}
