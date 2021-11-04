package com.furntrade.furntrademanagmentservet.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Order {
    private @Id
    @GeneratedValue
    Long id;
    @OneToOne
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductDetails> products;
    private Date shippmentDate;
    private float totalOrderPrice;
    private OrderStatus status;
    private String note1;
    private String note2;

    public Order() {
    }

    public Order(Customer customer, List<ProductDetails> products, Date shippmentDate, float totalOrderPrice, String note1, String note2) {
        this.customer = customer;
        this.products = products;
        this.shippmentDate = shippmentDate;
        this.totalOrderPrice = totalOrderPrice;
        this.note1 = note1;
        this.note2 = note2;
        this.status=OrderStatus.WAITING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductDetails> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetails> products) {
        this.products = products;
    }

    public Date getShippmentDate() {
        return shippmentDate;
    }

    public void setShippmentDate(Date shippmentDate) {
        this.shippmentDate = shippmentDate;
    }

    public float getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(float totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(order.totalOrderPrice, totalOrderPrice) == 0 && id.equals(order.id) && Objects.equals(customer, order.customer) && Objects.equals(products, order.products) && Objects.equals(shippmentDate, order.shippmentDate) && status == order.status && Objects.equals(note1, order.note1) && Objects.equals(note2, order.note2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products, shippmentDate, totalOrderPrice, status, note1, note2);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                ", shippmentDate=" + shippmentDate +
                ", totalOrderPrice=" + totalOrderPrice +
                ", status=" + status +
                ", note1='" + note1 + '\'' +
                ", note2='" + note2 + '\'' +
                '}';
    }
}
