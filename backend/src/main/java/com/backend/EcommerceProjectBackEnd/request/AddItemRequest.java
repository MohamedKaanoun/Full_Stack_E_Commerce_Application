package com.backend.EcommerceProjectBackEnd.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {
    private Integer productId;

    private String size;

    private int quantity;

    private Integer price;
}
