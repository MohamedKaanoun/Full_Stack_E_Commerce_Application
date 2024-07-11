package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Rating;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.request.RatingRequest;

import java.util.List;

public interface RatingService {
        public Rating createRating(RatingRequest request , User user) throws ProductException;

        public List<Rating> getProductsRating (Integer productId);
}
