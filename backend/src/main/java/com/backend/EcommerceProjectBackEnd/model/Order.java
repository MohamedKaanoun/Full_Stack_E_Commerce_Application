package com.backend.EcommerceProjectBackEnd.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @OneToOne
    private Address shippingAddress;

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();

    private double totalPrice;

    private Integer totalDiscountedPrice;

    private Integer discount;

    private String orderStatus;

    private Integer totalItems;
}
