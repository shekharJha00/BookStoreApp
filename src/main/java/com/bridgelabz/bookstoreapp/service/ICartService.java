package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.Cart;

import java.util.List;

public interface ICartService {
    Cart addToCart(Long userId, CartDTO cartDTO);
    List<Cart> findAllCarts();
    Object getCartById(Long cartId);
    String  editCartByCartId(Long userId, Long cartId, CartDTO cartDTO);
    void deleteCart(Long cartId);
}

