package com.Op1.product_service.dto;

import java.util.List;

public class ProductDTO {

    private Long id;
    private String name;
    private double price;
    private int quantity;
    private List<StockHistoryDTO> stockHistories;

    public ProductDTO(Long id, String name, double price, int quantity, List<StockHistoryDTO> stockHistories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.stockHistories = stockHistories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<StockHistoryDTO> getStockHistories() {
        return stockHistories;
    }

    public void setStockHistories(List<StockHistoryDTO> stockHistories) {
        this.stockHistories = stockHistories;
    }

}
