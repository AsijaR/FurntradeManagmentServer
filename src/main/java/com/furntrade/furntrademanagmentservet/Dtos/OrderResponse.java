package com.furntrade.furntrademanagmentservet.Dtos;


public class OrderResponse {

    private String status;
    private String productName;
    private float productPrice;

    public OrderResponse(String status, String productName, float productPrice) {
        this.status = status;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
