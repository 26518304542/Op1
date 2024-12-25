package com.Op1.order_service.dto;

public class UpdateStockRequest {

    private Long productId;
    private int quantityChange;


    public UpdateStockRequest(Long productId, int quantityChange) {
        this.productId = productId;
        this.quantityChange = quantityChange;
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
}
