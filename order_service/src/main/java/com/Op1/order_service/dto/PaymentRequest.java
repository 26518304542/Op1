package com.Op1.order_service.dto;

public class PaymentRequest {


    private Long orderId;
    private double amount;
    private Long customerId;
    private String paymentMethod;


    public PaymentRequest(Long orderId, double amount, Long customerId, String paymentMethod) {
        this.orderId = orderId;
        this.amount = amount;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
