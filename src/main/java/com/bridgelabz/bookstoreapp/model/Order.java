package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "order_table")
public @Data class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @JoinColumn(name = "cart_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Object cart;
    private String address;
    private LocalDate orderDate = LocalDate.now();
    private boolean cancel;

    public Order(Object cart, OrderDTO orderDTO) {
        this.cart = cart;
        this.orderDate = getOrderDate();
    }
}