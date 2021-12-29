package com.furntrade.furntrademanagmentservet.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class ProductOrderDetails implements Serializable{

    @EmbeddedId
    @JsonIgnore
    private ProdOrderId id;


    @ManyToOne
    @MapsId("orderId")
    @JsonIgnore
    Order order;

    @ManyToOne
    @MapsId("productId")
    Product product;

    private int quantity;

    public ProductOrderDetails() {
    }

    public ProductOrderDetails(Order order, Product product, int quantity) {
        this.id = new ProdOrderId(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public ProdOrderId getId() {
        return id;
    }

    public void setId(ProdOrderId id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
