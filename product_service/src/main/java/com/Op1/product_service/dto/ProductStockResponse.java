package com.Op1.product_service.dto;

public class ProductStockResponse {

    private Long productId;
    private int stock;



    public ProductStockResponse(Long productId, int stock) {
        this.productId = productId;
        this.stock = stock;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }


}
