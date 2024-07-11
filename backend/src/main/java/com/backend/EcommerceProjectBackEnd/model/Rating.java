package com.backend.EcommerceProjectBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;

    private double rating;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;


}
