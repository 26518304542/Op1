package com.Op1.payment_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Op1.payment_service.domain.Payment;
import com.Op1.payment_service.dto.PaymentRequest;
import com.Op1.payment_service.dto.UpdatePaymentStatusforOrderRequest;
import com.Op1.payment_service.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final WebClient.Builder webClientBuilder;


    public PaymentService(PaymentRepository paymentRepository,WebClient.Builder webClientBuilder ) {
        this.paymentRepository = paymentRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public String waitingForPayment(PaymentRequest paymentRequest){
        Payment payment = new Payment();
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setCustomerId(paymentRequest.getCustomerId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStatus("WAITING_FOR_PAYMENT");
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());

        paymentRepository.save(payment);

        return "Waiting fot payment";
    }

    public String processPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        updatePaymentStatusforOrder(payment);

        if (!"WAITING_FOR_PAYMENT".equals(payment.getPaymentStatus())){
            throw new RuntimeException("Payment is not in WAITING_FOR_PAYMENT state");
        }

        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);

        return "SUCCESS";
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    private void updatePaymentStatusforOrder(Payment payment){

        UpdatePaymentStatusforOrderRequest updatePaymentStatusforOrderRequest =
                                new UpdatePaymentStatusforOrderRequest(payment.getOrderId(), "SUCCESS");

        webClientBuilder.build()
                .patch()
                .uri("http://localhost:8080/orders/" + payment.getOrderId() + "/update-payment-status")
                .bodyValue(updatePaymentStatusforOrderRequest)
                .retrieve()
                .toBodilessEntity()
                .block();

    }




}
