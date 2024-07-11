package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.controller.UserController;
import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Cart;
import com.backend.EcommerceProjectBackEnd.model.CartItem;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.repository.CartRepository;
import com.backend.EcommerceProjectBackEnd.request.AddItemRequest;
import com.backend.EcommerceProjectBackEnd.service.CartService;
import com.backend.EcommerceProjectBackEnd.service.CartItemService;
import com.backend.EcommerceProjectBackEnd.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Integer userId, AddItemRequest request) throws ProductException, UserException {
        logger.debug("Adding item to cart for user ID: {}", userId);
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new UserException("Cart not found for user with id: " + userId);
        }

        Product product = productService.findProductById(request.getProductId());
        if (product == null) {
            throw new ProductException("Product not found with id: " + request.getProductId());
        }

        CartItem isPresent = cartItemService.isCartItemExist(cart, product, request.getSize(), userId);

        if (isPresent == null) {
            logger.debug("Product not present in cart, adding new item");
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);
            cartItem.setDiscountedPrice(product.getDiscountPrice());
            int price = product.getPrice() * request.getQuantity();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());
            cart.getCartItems().add(cartItem);
        } else {
            logger.debug("Product already in cart, updating quantity");
            isPresent.setQuantity(isPresent.getQuantity() + request.getQuantity());
            isPresent.setPrice(isPresent.getPrice() + (product.getPrice() * request.getQuantity()));
        }

        cartRepository.save(cart);
        return "Cart Item added successfully to Cart";
    }

    @Override
    public Cart findUserCart(Integer userId) throws UserException {
        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            throw new UserException("Cart not found for user with id: " + userId);
        }

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice =totalPrice + cartItem.getPrice();
            totalDiscountedPrice =totalDiscountedPrice + cartItem.getDiscountedPrice() * cartItem.getQuantity();
            totalItem +=totalItem + cartItem.getQuantity();
        }

        cart.setTotalItem(totalItem);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }
}

