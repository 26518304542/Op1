package com.Op1.payment_service.dto;

public class PaymentRequest {

    private Long orderId;
    private double amount;

    

    public PaymentRequest(Long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
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



}
