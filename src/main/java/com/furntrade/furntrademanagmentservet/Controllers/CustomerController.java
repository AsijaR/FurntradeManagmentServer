package com.furntrade.furntrademanagmentservet.Controllers;


import com.furntrade.furntrademanagmentservet.Exceptions.NotFoundExceptions.ObjectNotFoundException;
import com.furntrade.furntrademanagmentservet.ModelAssemblers.CustomerModelAssembler;
import com.furntrade.furntrademanagmentservet.Models.Customer;
import com.furntrade.furntrademanagmentservet.Repositories.CustomerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repository;
    private final CustomerModelAssembler assembler;

    public CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<Customer>> All()
    {
        List<EntityModel<Customer>> customers = repository.findAll().stream().
                map(assembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).All()).withSelfRel());
    }

    @PostMapping("/add")
    ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) {

        EntityModel<Customer> entityModel = assembler.toModel(repository.save(newCustomer));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Customer> One(@PathVariable Long id)
    {
        Customer customer=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));

        return assembler.toModel(customer);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> UpdateCustomer(@RequestBody Customer newCustomer,@PathVariable Long id)
    {
        Customer updateCustomer=repository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setAddress(newCustomer.getAddress());
                    customer.setCity(newCustomer.getCity());
                    customer.setState(newCustomer.getState());
                    customer.setZip(newCustomer.getZip());
                    customer.setContactPersonName(newCustomer.getContactPersonName());
                    customer.setContactPersonEmail(newCustomer.getContactPersonEmail());
                    return repository.save(customer);
                })
                .orElseGet(()->
                {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
        EntityModel<Customer> entityModel= assembler.toModel(updateCustomer);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id)
    {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
