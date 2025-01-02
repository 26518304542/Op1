package com.Op1.payment_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Op1.payment_service.domain.Payment;
import com.Op1.payment_service.dto.PaymentRequest;
import com.Op1.payment_service.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PostMapping("/waiting-for-payment")
    public ResponseEntity<String> waitingForPayment(@RequestBody PaymentRequest paymentRequest){
        String status = paymentService.waitingForPayment(paymentRequest);
        return ResponseEntity.ok(status);
    }

    @PatchMapping("/{id}/process-payment")
    public ResponseEntity<String> processPayment(@PathVariable Long id){
        String status = paymentService.processPayment(id);
        return ResponseEntity.ok(status);
    }

}
