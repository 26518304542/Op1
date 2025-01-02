package com.Op1.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Op1.order_service.domain.Order;
import com.Op1.order_service.dto.PaymentRequest;
import com.Op1.order_service.dto.UpdatePaymentStatusforOrderRequest;
import com.Op1.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updatedOrder(@PathVariable Long id, @RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @PatchMapping("/{id}/update-payment-status")
    public ResponseEntity<Void> updatePaymentStatus(@PathVariable Long id,@RequestBody UpdatePaymentStatusforOrderRequest request){
        orderService.updatePaymentStatus(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        orderService.deleteOrder(id);
    }

}
