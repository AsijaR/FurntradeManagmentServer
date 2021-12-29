package com.furntrade.furntrademanagmentservet.Models;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="orders")
public class Order{
    private @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductOrderDetails> orderedProducts = new ArrayList<>();
    @DateTimeFormat
    private Date shippmentDate;
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

    public List<ProductOrderDetails> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<ProductOrderDetails> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public void addProduct(Product product, int quantity) {
        ProductOrderDetails orderedProduct = new ProductOrderDetails(this,product,quantity);
        orderedProducts.add(orderedProduct);
        product.getProductOrderDetails().add(orderedProduct);
        totalOrderPrice+=product.getPrice()*quantity;

    }

    public void removeProduct(Product product,int quantity) {
        ProductOrderDetails orderedProduct = new ProductOrderDetails( this, product,0);
        product.getProductOrderDetails().remove(orderedProduct);
        orderedProducts.remove(orderedProduct);
        orderedProduct.setOrder(null);
        orderedProduct.setProduct(null);
        totalOrderPrice-=product.getPrice()*quantity;
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
        return Double.compare(order.totalOrderPrice, totalOrderPrice) == 0 && id.equals(order.id) && customer.equals(order.customer) && Objects.equals(shippmentDate, order.shippmentDate) && status == order.status && Objects.equals(note1, order.note1) && Objects.equals(note2, order.note2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, shippmentDate, totalOrderPrice, status, note1, note2);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + orderedProducts +
                ", shippmentDate=" + shippmentDate +
                ", totalOrderPrice=" + totalOrderPrice +
                ", status=" + status +
                ", note1='" + note1 + '\'' +
                ", note2='" + note2 + '\'' +
                '}';
    }
}
