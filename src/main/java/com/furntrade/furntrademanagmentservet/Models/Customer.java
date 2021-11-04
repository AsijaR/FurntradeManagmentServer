package com.furntrade.furntrademanagmentservet.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Customer {
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String contactPersonName;
    private String contactPersonEmail;

    public Customer() {
    }

    public Customer(String name, String address, String city, String state, int zip, String contactPersonName, String contactPersonEmail) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
    }

    public Customer(String name, String address, String city, String state, int zip) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return zip == customer.zip && id.equals(customer.id) && Objects.equals(name, customer.name) && Objects.equals(address, customer.address) && Objects.equals(city, customer.city) && Objects.equals(state, customer.state) && Objects.equals(contactPersonName, customer.contactPersonName) && Objects.equals(contactPersonEmail, customer.contactPersonEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, city, state, zip, contactPersonName, contactPersonEmail);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", contactPersonName='" + contactPersonName + '\'' +
                ", contactPersonEmail='" + contactPersonEmail + '\'' +
                '}';
    }
}
