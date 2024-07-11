package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Cart;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.request.AddItemRequest;
import com.backend.EcommerceProjectBackEnd.response.ApiResponse;
import com.backend.EcommerceProjectBackEnd.service.CartService;
import com.backend.EcommerceProjectBackEnd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@Tag(name = "Cart Management", description = "find user cart, add item to cart")
@RequiredArgsConstructor
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/")
    @Operation(description = "find cart by user id")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwt = sanitizeToken(authorizationHeader);
            logger.debug("JWT token after sanitization: {}", jwt);
            User user = userService.findUserProfileByJwt(jwt);
            Cart cart = cartService.findUserCart(user.getId());
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (UserException e) {
            logger.error("Error finding user cart: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/add")
    @Operation(description = "add item to cart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
                                                     @RequestHeader("Authorization") String authorizationHeader) {
        try {
            logger.debug("Add item request: {}", req);
            String jwt = sanitizeToken(authorizationHeader);
            logger.debug("JWT token after sanitization: {}", jwt);
            User user = userService.findUserProfileByJwt(jwt);
            logger.debug("User found: {}", user);
            cartService.addCartItem(user.getId(), req);
            ApiResponse res = new ApiResponse();
            res.setMessage("Item added successfully to your cart");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (UserException | ProductException e) {
            logger.error("Error adding item to cart: {}", e.getMessage());
            ApiResponse res = new ApiResponse();
            res.setMessage(e.getMessage());
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    private String sanitizeToken(String authorizationHeader) throws UserException {
        if (authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7).trim();
        }
        throw new UserException("Invalid Authorization header format");
    }
}



