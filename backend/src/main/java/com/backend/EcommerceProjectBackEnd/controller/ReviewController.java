package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Review;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.request.ReviewRequest;
import com.backend.EcommerceProjectBackEnd.service.ReviewService;
import com.backend.EcommerceProjectBackEnd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {
    final private UserService userService;
    final private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request , @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(request , user);
        return new ResponseEntity<>(review , HttpStatus.CREATED);

    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getAllProductReviews(@PathVariable("productId") Integer productId , @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        List<Review> reviews = reviewService.getAllProductReview(productId);

        return new ResponseEntity<>(reviews , HttpStatus.ACCEPTED);
    }
}
