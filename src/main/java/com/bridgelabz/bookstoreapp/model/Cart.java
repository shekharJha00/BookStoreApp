package com.bridgelabz.bookstoreapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long cartId;
    @JoinColumn(name = "user_id")
    @OneToOne()
    private User user;
    @JoinColumn(name = "Book_id")
    @ManyToOne()
    private Book book;
    private int quantity;
    private double totalPrice;

    public Cart(User user, Book book, int quantity, double totalPrice) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}