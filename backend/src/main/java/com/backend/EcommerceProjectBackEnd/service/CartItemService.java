package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.exception.CardItemException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Cart;
import com.backend.EcommerceProjectBackEnd.model.CartItem;
import com.backend.EcommerceProjectBackEnd.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Integer userId,Integer id , CartItem cartItem) throws Exception;

    public CartItem isCartItemExist(Cart cart , Product product , String size , Integer userId);

    public void removeCartItem(Integer userId , Integer cardItemId) throws Exception;

    public CartItem findCartItemById(Integer id) throws CardItemException;




}
