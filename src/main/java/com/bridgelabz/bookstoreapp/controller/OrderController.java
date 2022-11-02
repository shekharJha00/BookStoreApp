package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Order;
import com.bridgelabz.bookstoreapp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore/order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;
    @PostMapping("/place")
    public ResponseEntity<ResponseDTO> placeOrderDetails(@RequestBody OrderDTO orderDTO){
        Order response = iOrderService.addOrderDetails(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Thank you for your order! Order Details:", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable("orderId") Long orderId) {
        iOrderService.deleteOrderByOrderId(orderId);
        ResponseDTO responseDTO = new ResponseDTO("Order cancelled successfully", "Order id " + orderId );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping( "/getAll")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<Order> orderDataList = iOrderService.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Orders: ", orderDataList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getAllForUser/{orderId}")
    public ResponseEntity<ResponseDTO> findOrderByOrderID(@PathVariable Long orderId){
        Order orderDetails = iOrderService.getOrderDetailsByOrderId(orderId);
        ResponseDTO responseDTO = new ResponseDTO("Order Details with Order ID: "+orderId, orderDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
