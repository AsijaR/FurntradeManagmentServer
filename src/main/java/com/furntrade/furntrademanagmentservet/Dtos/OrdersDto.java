package com.furntrade.furntrademanagmentservet.Dtos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furntrade.furntrademanagmentservet.Models.Customer;
import org.springframework.hateoas.RepresentationModel;

public class OrdersDto extends RepresentationModel<OrdersDto> {

    private Long id;

    private String shippmentDate;

    private String status;

    private String note1;

    private String note2;

    private String CustomerName;

    @JsonIgnore
    private Customer c;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }

    public String getShippmentDate() {
        return shippmentDate;
    }

    public void setShippmentDate(String shippmentDate) {
        this.shippmentDate = shippmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

}
