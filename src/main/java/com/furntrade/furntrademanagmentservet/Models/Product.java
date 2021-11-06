package com.furntrade.furntrademanagmentservet.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Product {
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String model;
    private String color;
    private String material;
    private double price;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private Set<ProductOrderDetails> productOrderDetailsSet;
    public Product() {
    }

    public Product(String name, String model, String color, String material, float price) {
        this.name = name;
        this.model = model;
        this.color = color;
        this.material = material;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && id.equals(product.id) && Objects.equals(name, product.name) && Objects.equals(model, product.model) && Objects.equals(color, product.color) && Objects.equals(material, product.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, model, color, material, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", price=" + price +
                '}';
    }
}
