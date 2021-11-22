package com.furntrade.furntrademanagmentservet.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ProductOrderDetails.class)
public class ProductOrderDetails implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    Order order;

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="product_id")
    Product product;

    private int quantity;

    public ProductOrderDetails() {
    }

    public ProductOrderDetails(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public double getPriceOfProduct() {
        return product.getPrice()*quantity;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", totalPriceForProducts=" + getPriceOfProduct() +
                '}';
    }
}
