package com.furntrade.furntrademanagmentservet.ModelAssemblers;
import com.furntrade.furntrademanagmentservet.Controllers.OrderController;
import com.furntrade.furntrademanagmentservet.Dtos.OrdersDto;
import com.furntrade.furntrademanagmentservet.Models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, OrdersDto> {
    @Override
    public OrdersDto toModel(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        OrdersDto ordersDto = modelMapper.map(order, OrdersDto.class);
        Link selfLink = linkTo(methodOn(OrderController.class).One(order.getId())).withSelfRel();
        ordersDto.add(selfLink);

        return ordersDto;
    }

    @Override
    public CollectionModel<OrdersDto> toCollectionModel(Iterable<? extends Order> ordersList) {
        ModelMapper modelMapper = new ModelMapper();
        List<OrdersDto> ordersDTOS = new ArrayList<>();

        for (Order order : ordersList){
            OrdersDto ordersDto = modelMapper.map(order, OrdersDto.class);
            ordersDto.add(linkTo(methodOn(OrderController.class).One(order.getId())).withSelfRel());
            ordersDTOS.add(ordersDto);
        }

        return new CollectionModel<OrdersDto>(ordersDTOS);
    }
}
