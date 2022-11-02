package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore/cart")
public
class CartController {
    @Autowired
    private ICartService iCartService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<ResponseDTO> addCartDetails(@PathVariable Long userId, @RequestBody CartDTO cartDTO) {
        Cart cartDetails = iCartService.addToCart(userId, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Cart Details Added", cartDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCart(@PathVariable("cartId") Long cartId) {
        iCartService.deleteCart(cartId);
        ResponseDTO responseDTO = new ResponseDTO("cart number " + cartId + " deleted successfully", cartId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}/{cartId}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable Long userId, @PathVariable Long cartId, @RequestBody CartDTO cartDTO) {
        String response = iCartService.editCartByCartId(userId, cartId, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Cart Data with Cart ID: " + cartId, response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> findAllCarts() {
        Iterable<Cart> allCarts = iCartService.findAllCarts();
        ResponseDTO responseDTO = new ResponseDTO("CartData Details: ", allCarts);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllForUser/{cartId}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable("cartId") Long cartId) {
        Object cartData = iCartService.getCartById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Product details for cart Id " + cartId, cartData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
