package com.Op1.order_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Op1.order_service.domain.Order;
import com.Op1.order_service.dto.PaymentRequest;
import com.Op1.order_service.dto.ProductResponse;
import com.Op1.order_service.dto.ProductUpdateOrderIdRequest;
import com.Op1.order_service.dto.UpdatePaymentStatusforOrderRequest;
import com.Op1.order_service.dto.UpdateStockRequest;
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

        /*
        ProductResponse product =  webClientBuilder.build()
            .get()
            .uri("http://localhost:8080/products/" + order.getProductId())
            .retrieve()
            .bodyToMono(ProductResponse.class)
            .block();

        if (product == null){
            throw new RuntimeException("Product was not found!");
        }

        if(product.getQuantity() < order.getQuantity()){
            throw new RuntimeException("Not enough stock available for the product!");
        }

        order.setPrice(product.getPrice()*order.getQuantity());
        order.setOrderDate(LocalDateTime.now());


        webClientBuilder.build()
            .put()
            .uri("http://localhost:8080/products/" + order.getProductId() + "/update-stock")
            .bodyValue(new UpdateStockRequest(order.getProductId(), -order.getQuantity()))
            .retrieve()
            .toBodilessEntity()
            .block();

            After determining the reason of the error belongs to updateRequest in common_library it will be used
        */

        ProductResponse product = checkProductAvailablity(order);

        /*webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/products/" + order.getProductId())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();*/

        if (product == null) {
            throw new RuntimeException("Product not found!");
        }

        // Stok kontrolü
        if (product.getQuantity() < order.getQuantity()) {
            throw new RuntimeException("Not enough stock available for the product!");
        }

        // Sipariş detaylarını hesapla
        order.setPrice(product.getPrice() * order.getQuantity());
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerId(order.getCustomerId());
        order.setPaymentType(order.getPaymentType());
        order.setPaymentSTATUS("WAITING_FOR_PAYMENT");
        Order savedOrder = orderRepository.save(order);

        // Stok güncellemesi yap
        PaymentRequest paymentRequest = new PaymentRequest(savedOrder.getId(), savedOrder.getPrice(), savedOrder.getCustomerId(), savedOrder.getPaymentType());
        webClientBuilder.build()
            .post()
            .uri("http://localhost:8080/payments/waiting-for-payment")
            .bodyValue(paymentRequest)
            .retrieve()
            .toBodilessEntity()
            .block();

        updateProductStock(order);

        // Siparişi kaydet ve geri döndür
        return orderRepository.save(order);

    }

    private ProductResponse checkProductAvailablity(Order order){

        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/products/" + order.getProductId())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }

    private void updateProductStock(Order order){

        UpdateStockRequest updateStockRequest = new UpdateStockRequest(order.getProductId(), -order.getQuantity(), order.getId());

        webClientBuilder.build()
                .patch()
                .uri("http://localhost:8080/products/" + order.getProductId() + "/update-stock")
                .bodyValue(updateStockRequest)
                .retrieve()
                .toBodilessEntity()
                .block();

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

    public String updatePaymentStatus(Long id, UpdatePaymentStatusforOrderRequest request) {
        Order order = getOrderById(id);

        order.setPaymentSTATUS(request.getStatus());
        
        orderRepository.save(order);

        return "SUCCESS";
    }

}
