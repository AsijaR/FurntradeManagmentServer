package com.furntrade.furntrademanagmentservet.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ProductOrderDetails implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    Order order;

    @Id
    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;

    private int quantity;

    public ProductOrderDetails() {
    }

    public ProductOrderDetails(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderDetails that = (ProductOrderDetails) o;
        return Objects.equals(order,that.order) && Objects.equals(product,that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
