package com.Op1.order_service.dto;

public class ProductUpdateOrderIdRequest {

    private Long orderId;

    public ProductUpdateOrderIdRequest(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
