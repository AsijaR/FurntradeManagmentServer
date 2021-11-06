package com.furntrade.furntrademanagmentservet.ModelAssemblers;

import com.furntrade.furntrademanagmentservet.Controllers.CustomerController;
import com.furntrade.furntrademanagmentservet.Models.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer, //
                linkTo(methodOn(CustomerController.class).One(customer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).All()).withRel("customers"));
    }
}