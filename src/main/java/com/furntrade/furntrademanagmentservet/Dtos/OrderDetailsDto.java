package com.furntrade.furntrademanagmentservet.Dtos;

import com.furntrade.furntrademanagmentservet.Models.Customer;
import com.furntrade.furntrademanagmentservet.Models.Product;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class OrderDetailsDto extends RepresentationModel<OrdersDto> {

    private OrdersDto ordersDto;
    private Customer customer;
    private List<ProductDto> products;

    public OrdersDto getOrdersDto() {
        return ordersDto;
    }

    public void setOrdersDto(OrdersDto ordersDto) {
        this.ordersDto = ordersDto;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }


}
