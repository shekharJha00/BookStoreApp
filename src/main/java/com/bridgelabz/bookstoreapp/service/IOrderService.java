package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.model.Order;

import java.util.List;

public interface IOrderService {
    Order addOrderDetails(OrderDTO orderDTO);

    Order getOrderDetailsByOrderId(Long orderId);

    void deleteOrderByOrderId(Long orderId);

    List<Order> getAllOrders();
}

