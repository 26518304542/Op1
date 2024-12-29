package com.Op1.payment_service.service;

import org.springframework.stereotype.Service;

import com.Op1.payment_service.domain.Payment;
import com.Op1.payment_service.dto.PaymentRequest;
import com.Op1.payment_service.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public String processPayment(PaymentRequest paymentRequest){
        Payment payment = new Payment();
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStatus("SUCCESS");

        paymentRepository.save(payment);

        return "Payment successfull for Order ID " + paymentRequest.getOrderId();
    }


}
