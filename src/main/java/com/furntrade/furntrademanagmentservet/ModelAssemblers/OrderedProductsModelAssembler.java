package com.furntrade.furntrademanagmentservet.ModelAssemblers;

import com.furntrade.furntrademanagmentservet.Controllers.OrderController;
import com.furntrade.furntrademanagmentservet.Dtos.ProductOrderDetailsDto;
import com.furntrade.furntrademanagmentservet.Models.Order;
import com.furntrade.furntrademanagmentservet.Models.ProductOrderDetails;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class OrderedProductsModelAssembler implements RepresentationModelAssembler<ProductOrderDetails, ProductOrderDetailsDto> {
        @Override
        public ProductOrderDetailsDto toModel(ProductOrderDetails entity) {
                ModelMapper modelMapper = new ModelMapper();
                ProductOrderDetailsDto orderedProductDto = modelMapper.map(entity, ProductOrderDetailsDto.class);
               // Link selfLink = linkTo(methodOn(OrderController.class).One(entity.getId())).withSelfRel();
             //   orderedProductDto.add(selfLink);
                return orderedProductDto;

        }

        @Override
        public CollectionModel<ProductOrderDetailsDto> toCollectionModel(Iterable<? extends ProductOrderDetails> entities) {
                return RepresentationModelAssembler.super.toCollectionModel(entities);
        }
}