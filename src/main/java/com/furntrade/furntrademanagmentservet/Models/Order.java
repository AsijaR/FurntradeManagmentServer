package com.furntrade.furntrademanagmentservet.Models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    private @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH},orphanRemoval = true)
    private Set<ProductOrderDetails> productOrderDetails;
    @DateTimeFormat
    private Date shippmentDate;
   // DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ISO_WEEK_DATE.ofPattern("dd.MM.yyyy");
    private double totalOrderPrice;
    private OrderStatus status;
    private String note1;
    private String note2;

    public Order() {
    }

    public Order(Customer customer, Date shippmentDate, String note1, String note2) {
        this.customer = customer;
        this.shippmentDate = shippmentDate;
        this.note1 = note1;
        this.note2 = note2;
        this.status=OrderStatus.WAITING;
        this.totalOrderPrice=productOrderDetails.stream().mapToDouble(o->o.getPriceOfProduct()).sum();
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

    public Set<ProductOrderDetails> getProductOrderDetails() {
        return productOrderDetails;
    }

    public void setProductOrderDetails(Set<ProductOrderDetails> productOrderDetails) {
        this.productOrderDetails = productOrderDetails;
    }

    public Date getShippmentDate() {
        return shippmentDate;
    }

    public void setShippmentDate(Date shippmentDate) {
        this.shippmentDate = shippmentDate;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
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
        return Double.compare(order.totalOrderPrice, totalOrderPrice) == 0 && id.equals(order.id) && Objects.equals(customer, order.customer) && Objects.equals(productOrderDetails, order.productOrderDetails) && Objects.equals(shippmentDate, order.shippmentDate) && status == order.status && Objects.equals(note1, order.note1) && Objects.equals(note2, order.note2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, productOrderDetails, shippmentDate, totalOrderPrice, status, note1, note2);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + productOrderDetails +
                ", shippmentDate=" + shippmentDate +
                ", totalOrderPrice=" + totalOrderPrice +
                ", status=" + status +
                ", note1='" + note1 + '\'' +
                ", note2='" + note2 + '\'' +
                '}';
    }
}
