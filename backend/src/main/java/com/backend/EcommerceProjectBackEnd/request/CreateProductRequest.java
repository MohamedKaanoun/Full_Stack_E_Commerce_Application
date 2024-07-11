package com.backend.EcommerceProjectBackEnd.request;

import com.backend.EcommerceProjectBackEnd.model.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String title;

    private String description;

    private int price;

    private int discountPrice;

    private int discountPercent;

    private int quantity;

    private String brand;

    private String color;

    private Set<Size> size = new HashSet<>();

    private String imageUrl;

    private String topLevelCategory;

    private String secondLevelCategory;

    private String thirdLevelCategory;


}
