package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Review;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest request , User user) throws ProductException;

    public List<Review> getAllProductReview(Integer productId);
}
