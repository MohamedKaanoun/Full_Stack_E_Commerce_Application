package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.model.Review;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.repository.ReviewRepository;
import com.backend.EcommerceProjectBackEnd.request.ReviewRequest;
import com.backend.EcommerceProjectBackEnd.service.ProductService;
import com.backend.EcommerceProjectBackEnd.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepository repository;
    private ProductService productService;
    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(request.getReview());

        return repository.save(review);
    }

    @Override
    public List<Review> getAllProductReview(Integer productId) {
        return repository.getAllProductsReview(productId);
    }
}
