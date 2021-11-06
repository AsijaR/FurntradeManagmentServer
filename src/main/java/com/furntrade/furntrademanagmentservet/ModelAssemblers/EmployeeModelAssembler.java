package com.furntrade.furntrademanagmentservet.ModelAssemblers;

import com.furntrade.furntrademanagmentservet.Controllers.EmployeeController;
import com.furntrade.furntrademanagmentservet.Models.AppUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<AppUser, EntityModel<AppUser>> {

@Override
public EntityModel<AppUser> toModel(AppUser employee) {
        return EntityModel.of(employee, //
        linkTo(methodOn(EmployeeController.class).One(employee.getId())).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).All()).withRel("employees"));
        }
}