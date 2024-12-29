package com.Op1.order_service.dto;

public class UpdateStockRequest {

    private Long productId;
    private int quantityChange;
    private Long orderId;





    public UpdateStockRequest(Long productId, int quantityChange, Long orderId) {
        this.productId = productId;
        this.quantityChange = quantityChange;
        this.orderId = orderId;
    }


    public Long getProductId() {
        return productId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public int getQuantityChange() {
        return quantityChange;
    }


    public void setQuantityChange(int quantityChange) {
        this.quantityChange = quantityChange;
    }

    public Long getOrderId() {
        return orderId;
    }


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
