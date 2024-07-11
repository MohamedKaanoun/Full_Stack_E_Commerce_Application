package com.backend.EcommerceProjectBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private int price;

    private String description;
    @Column(name = "discount_price")
    private int discountPrice;

    @Column(name = "discount_percent")
    private int discountPercent;

    private int quantity;

    private String brand;

    private String color;

    @Embedded
    @ElementCollection
    @Column(name = "sizes")
    private Set<Size> sizes = new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    private int numRatings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

}
