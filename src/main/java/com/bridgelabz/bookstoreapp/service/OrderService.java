package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Order;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ICartService iCartService;

    public Order addOrderDetails(OrderDTO orderDTO) {
        Order order = new Order(iCartService.getCartById(Long.valueOf(orderDTO.getCartId())), orderDTO);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderDetailsByOrderId(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return (Order) order.get();
        } else
            throw new BookStoreException("Order ID does not exist");
    }

    @Override
    public void deleteOrderByOrderId(Long orderId) {
        Order order = this.getOrderDetailsByOrderId(orderId);
        order.setCancel(true);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}