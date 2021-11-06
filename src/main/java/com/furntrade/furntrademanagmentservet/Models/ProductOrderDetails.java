package com.furntrade.furntrademanagmentservet.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ProductOrderDetails.class)
public class ProductOrderDetails implements Serializable {
    @Id @ManyToOne Order order;

    @Id @ManyToOne Product product;

    private int quantity;

    private double totalPrice;

    public ProductOrderDetails() {
    }

    public ProductOrderDetails(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.totalPrice = quantity*product.getPrice();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTotalPriceForProducts() {
        return totalPrice;
    }

    public void setTotalPriceForProducts(double totalPriceForProducts) {
        this.totalPrice = totalPriceForProducts;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", totalPriceForProducts=" + totalPrice +
                '}';
    }
}
