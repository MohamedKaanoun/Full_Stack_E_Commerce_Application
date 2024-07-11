package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.exception.CardItemException;
import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.Cart;
import com.backend.EcommerceProjectBackEnd.model.CartItem;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.repository.CartItemRepository;
import com.backend.EcommerceProjectBackEnd.repository.CartRepository;
import com.backend.EcommerceProjectBackEnd.service.CartItemService;
import com.backend.EcommerceProjectBackEnd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImplementation implements CartItemService {

    final private CartItemRepository cartItemRepository;
    final private UserService userService;
    final private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountPrice()*cartItem.getQuantity());
        return cartItemRepository.save(cartItem);

    }

    @Override
    public CartItem updateCartItem(Integer userId,Integer id , CartItem cartItem) throws Exception {
        CartItem cartItem1 = findCartItemById(id);
        User user = userService.findUserById(userId);

        if(user.getId().equals(userId)){
            cartItem1.setQuantity(cartItem.getQuantity());
             cartItem1.setPrice(cartItem.getPrice());
             cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountPrice());

        }

        return cartItemRepository.save(cartItem1);

    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Integer userId) {
        return cartItemRepository.isCartItemExist(cart , product , size , userId);
    }

    @Override
    public void removeCartItem(Integer userId, Integer cardItemId) throws Exception {
        CartItem cartItem = findCartItemById(cardItemId);
        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.delete(cartItem);
        }
        else
            throw new UserException("User with id : "+userId+" is not exist");
    }

    @Override
    public CartItem findCartItemById(Integer id) throws CardItemException {
        Optional<CartItem> opt = cartItemRepository.findById(id);

        if(opt.isPresent()){
        return opt.get();
        }else{
            throw new CardItemException("There no Cart Item with this id : "+id);
        }
    }
}
