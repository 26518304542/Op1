package com.Op1.product_service.dto;

public class StockHistoryDTO {

    private Long id;
    private Long orderId;
    private int quantityChange;

    public StockHistoryDTO(Long id, Long orderId, int quantityChange) {
        this.id = id;
        this.orderId = orderId;
        this.quantityChange = quantityChange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

    public void setQuantityChange(int quantityChange) {
        this.quantityChange = quantityChange;
    }


    

}
