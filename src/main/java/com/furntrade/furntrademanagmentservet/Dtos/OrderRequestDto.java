package com.furntrade.furntrademanagmentservet.Dtos;

import com.furntrade.furntrademanagmentservet.Models.Order;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestDto extends RepresentationModel<OrderRequestDto> {
    private OrdersDto order;
    private List<ProductOrderDetailsDto> products=new ArrayList<>();

    public OrdersDto getOrder() {
        return order;
    }

    public void setOrder(OrdersDto order) {
        this.order = order;
    }

    public List<ProductOrderDetailsDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrderDetailsDto> products) {
        this.products = products;
    }
}
