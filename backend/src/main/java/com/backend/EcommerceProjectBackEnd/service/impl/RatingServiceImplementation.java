package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.model.Rating;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.repository.RatingRepository;
import com.backend.EcommerceProjectBackEnd.request.RatingRequest;
import com.backend.EcommerceProjectBackEnd.service.ProductService;
import com.backend.EcommerceProjectBackEnd.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImplementation implements RatingService {
    final private RatingRepository ratingRepository;
    final private ProductService productService;

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setRating(request.getRating());
        rating.setUser(user);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Integer productId) {
        return ratingRepository.getALlProductsRating(productId);
    }
}
