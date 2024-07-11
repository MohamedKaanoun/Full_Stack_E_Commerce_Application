package com.backend.EcommerceProjectBackEnd.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private String name;
    private CategoryDTO parentCategory;
}
