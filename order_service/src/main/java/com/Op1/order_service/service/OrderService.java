package com.Op1.order_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Op1.order_service.domain.Order;
import com.Op1.order_service.dto.ProductResponse;
import com.Op1.order_service.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public OrderService(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    public Order createOrder(Order order){
        if (order.getProductId() == null) {
            throw new RuntimeException("Product ID must be provided!");
        }

        ProductResponse product =  webClientBuilder.build()
            .get()
            .uri("http://localhost:8080/products/" + order.getProductId())
            .retrieve()
            .bodyToMono(ProductResponse.class)
            .block();

        if (product == null){
            throw new RuntimeException("Product was not found!");
        }

        order.setPrice(product.getPrice()*order.getQuantity());
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder){
        Order order = getOrderById(id);
        order.setPrice(updatedOrder.getPrice());
        order.setQuantity(updatedOrder.getQuantity());
        order.setOrderDate(updatedOrder.getOrderDate());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

}
