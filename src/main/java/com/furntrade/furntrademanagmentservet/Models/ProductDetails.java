package com.furntrade.furntrademanagmentservet.Models;

import javax.persistence.*;

@Entity
@Table
public class ProductDetails {
    private @Id
    @GeneratedValue
    Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private float totalPrice;

    public ProductDetails() {
    }

    public ProductDetails(int quantity, Product product, float totalPrice) {
        this.quantity = quantity;
        this.product = product;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
