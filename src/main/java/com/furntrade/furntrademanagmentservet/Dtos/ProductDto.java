package com.furntrade.furntrademanagmentservet.Dtos;

import org.springframework.hateoas.RepresentationModel;

public class ProductDto extends RepresentationModel<ProductDto> {

    private String name;
    private String model;
    private String color;
    private String price;
    private int productOrderDetailsSetQuantity;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProductOrderDetailsSetQuantity() {
        return productOrderDetailsSetQuantity;
    }

    public void setProductOrderDetailsSetQuantity(int productOrderDetailsSetQuantity) {
        this.productOrderDetailsSetQuantity = productOrderDetailsSetQuantity;
    }
}
