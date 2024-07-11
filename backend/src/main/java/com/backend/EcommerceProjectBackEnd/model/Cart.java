package com.backend.EcommerceProjectBackEnd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL , orphanRemoval = true)
    @Column(name = "cart_items")
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "total_item")
    private int totalItem;

    private int totalDiscountedPrice;

    private int discount;

}
