package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.User;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRegistrationRepository;
    @Autowired
    BookRepository bookRepository;
    @Override
    public Cart addToCart(Long userId, CartDTO cartDTO){
        java.util.Optional<User> user = userRegistrationRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        if(user.isPresent()){
            if(book.isPresent()) {
                if(cartDTO.getQuantity()<=book.get().getBookQuantity()){
                   double totalPrice = book.get().getBookPrice()*cartDTO.getQuantity();
                    Cart cartDetails = new Cart(user.get(), book.get(), (int) cartDTO.getQuantity(),totalPrice);
                    return cartRepository.save(cartDetails);
                }else
                    throw new BookStoreException("Quantity Exceeds, Available Book Quantity: "+book.get().getBookQuantity());
            }else
                throw new BookStoreException("Book Does not exist: Invalid BookId");
        }else
            throw new BookStoreException("User Does not exist: Invalid UserId");
    }
    @Override
    public List<Cart> findAllCarts() {
        List<Cart> cartList = cartRepository.findAll();
        if(cartList.isEmpty()){
            throw new BookStoreException("No Items added in cart yet!!");
        }else
            return cartList;
    }
    @Override
    public Object getCartById(Long cartId) {
        java.util.Optional<Cart> cartDetails = cartRepository.findById(cartId);
        if (cartDetails.isPresent()) {
            return cartDetails.get();
        } else
            throw new BookStoreException("Cart ID does not exist: Invalid ID");
    }
    @Override
    public String editCartByCartId(Long userId, Long cartId, CartDTO cartDTO) {
        java.util.Optional<User> userData = userRegistrationRepository.findById(userId);
        java.util.Optional<Cart> cartDetails = cartRepository.findById(cartId);
        java.util.Optional<Book> bookDetails = bookRepository.findById(cartDTO.getBookId());
        if(cartDetails.isPresent() && userData.isPresent()){
            if(bookDetails.isPresent()){
                cartDetails.get().setBook(bookDetails.get());
                if(cartDTO.getQuantity()<=bookDetails.get().getBookQuantity()){
                    cartDetails.get().setQuantity((int) cartDTO.getQuantity());
                    cartDetails.get().setTotalPrice(cartDTO.getQuantity()*bookDetails.get().getBookPrice());
                    cartRepository.save(cartDetails.get());
                    return "Cart Details Updated Quantity: "+cartDTO.getQuantity()+" Total Price: "+cartDetails.get().getTotalPrice();
                }else
                    throw new BookStoreException("Quantity Exceeds, Available Book Quantity: "+bookDetails.get().getBookQuantity());
            }else
                throw new BookStoreException("Book ID does not exist: Invalid Book ID");
        }else
            throw new BookStoreException("Invalid Cart ID or User ID!");
    }
    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}