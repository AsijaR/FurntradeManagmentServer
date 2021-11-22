package com.furntrade.furntrademanagmentservet.ModelAssemblers;

import com.furntrade.furntrademanagmentservet.Controllers.ProductController;
import com.furntrade.furntrademanagmentservet.Models.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>>{
    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).One(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).AllWithJavaconfig()).withRel("products"));
    }
}