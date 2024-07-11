package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Rating;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.request.RatingRequest;
import com.backend.EcommerceProjectBackEnd.service.RatingService;
import com.backend.EcommerceProjectBackEnd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
public class RatingController {
    final private UserService userService;
    final private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request , @RequestHeader("Authorization") String jwt) throws ProductException , UserException{
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(request , user);
        return new ResponseEntity<Rating>(rating , HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRatings(@PathVariable("productId") Integer productId , @RequestHeader("Authorization") String jwt) throws ProductException , UserException{
        User user = userService.findUserProfileByJwt(jwt);

        List<Rating> ratings = ratingService.getProductsRating(productId);

        return new ResponseEntity<>(ratings , HttpStatus.CREATED);
    }
}
