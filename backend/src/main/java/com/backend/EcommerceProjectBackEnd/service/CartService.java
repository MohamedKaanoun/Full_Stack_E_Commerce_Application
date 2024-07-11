package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Cart;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Integer userId , AddItemRequest request) throws ProductException, UserException;

    public Cart findUserCart(Integer userId) throws UserException;


}
