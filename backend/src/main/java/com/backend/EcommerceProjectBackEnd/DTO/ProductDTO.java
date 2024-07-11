package com.backend.EcommerceProjectBackEnd.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {
    private Integer id;
    private String title;
    private int price;
    private String description;
    private int discountPrice;
    private int discountPercent;
    private int quantity;
    private String brand;
    private String color;
    private Set<SizeDTO> sizes;
    private String imageUrl;
    private int numRatings;
    private LocalDateTime createdDate;
    private CategoryDTO category;
}

