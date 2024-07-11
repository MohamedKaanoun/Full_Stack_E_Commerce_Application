package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.CardItemException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.CartItem;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.response.ApiResponse;
import com.backend.EcommerceProjectBackEnd.service.CartItemService;
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
@RequestMapping("/carts/cartItems")
@Tag(name = "Cart Item Management", description = "Create, update, and remove cart items")
@RequiredArgsConstructor
public class CartItemController {

    private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);
    private final CartItemService cartItemService;
    private final UserService userService;

    @PostMapping("/")
    @Operation(description = "Create a new cart item")
    public ResponseEntity<ApiResponse> createCartItem(@RequestBody CartItem cartItem) {
        try {
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            ApiResponse res = new ApiResponse();
            res.setMessage("Cart item created successfully");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating cart item: {}", e.getMessage());
            ApiResponse res = new ApiResponse();
            res.setMessage(e.getMessage());
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Update an existing cart item")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Integer id,
                                                      @RequestBody CartItem cartItem,
                                                      @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwt = sanitizeToken(authorizationHeader);
            logger.debug("JWT token after sanitization: {}", jwt);
            User user = userService.findUserProfileByJwt(jwt);

            CartItem existingCartItem = cartItemService.findCartItemById(id);

            // Only update the quantity field
            existingCartItem.setQuantity(cartItem.getQuantity());

            CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), id, existingCartItem);
            ApiResponse res = new ApiResponse();
            res.setMessage("Cart item updated successfully");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (UserException | CardItemException e) {
            logger.error("Error updating cart item: {}", e.getMessage());
            ApiResponse res = new ApiResponse();
            res.setMessage(e.getMessage());
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error updating cart item: {}", e.getMessage());
            ApiResponse res = new ApiResponse();
            res.setMessage("Unexpected error occurred");
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    @Operation(description = "Remove an existing cart item")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Integer id,
                                                      @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwt = sanitizeToken(authorizationHeader);
            logger.debug("JWT token after sanitization: {}", jwt);
            User user = userService.findUserProfileByJwt(jwt);
            cartItemService.removeCartItem(user.getId(), id);
            ApiResponse res = new ApiResponse();
            res.setMessage("Cart item removed successfully");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (UserException | CardItemException e) {
            logger.error("Error removing cart item: {}", e.getMessage());
            ApiResponse res = new ApiResponse();
            res.setMessage(e.getMessage());
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error removing cart item: {}", e.getMessage());
            ApiResponse res = new ApiResponse();
            res.setMessage("Unexpected error occurred");
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String sanitizeToken(String authorizationHeader) throws UserException {
        if (authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7).trim();
        }
        throw new UserException("Invalid Authorization header format");
    }
}
