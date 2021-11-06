package com.furntrade.furntrademanagmentservet.Controllers;

import com.furntrade.furntrademanagmentservet.Exceptions.NotFoundExceptions.ObjectNotFoundException;
import com.furntrade.furntrademanagmentservet.ModelAssemblers.OrderModelAssembler;
import com.furntrade.furntrademanagmentservet.Models.Order;
import com.furntrade.furntrademanagmentservet.Models.OrderStatus;
import com.furntrade.furntrademanagmentservet.Repositories.OrderRepository;
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
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository repository;
    private final OrderModelAssembler assembler;

    public OrderController(OrderRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    @GetMapping()
    public CollectionModel<EntityModel<Order>> All()
    {
        List<EntityModel<Order>> Orders = repository.findAll().stream().
                map(assembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(Orders, linkTo(methodOn(OrderController.class).All()).withSelfRel());
    }
    @PostMapping("/add")
    ResponseEntity<?> newOrder(@RequestBody Order newOrder) {

        EntityModel<Order> entityModel = assembler.toModel(repository.save(newOrder));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Order> One(@PathVariable Long id)
    {
        Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));

        return assembler.toModel(order);
    }
    @PutMapping("/change-status/{id}")
    ResponseEntity<?> updateOrderStatus(@RequestBody OrderStatus status, @PathVariable Long id)
    {
        Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
        order.setStatus(status);
        return ResponseEntity.ok(assembler.toModel(repository.save(order)));
    }
    @PutMapping("/update/{id}")
    ResponseEntity<?> updateOrder(@RequestBody Order newOrder,@PathVariable Long id)
    {
        Order updateOrder=repository.findById(id)
                .map(Order -> {
                    Order.setCustomer(newOrder.getCustomer());
                    Order.setShippmentDate(newOrder.getShippmentDate());
                    Order.setNote1(newOrder.getNote1());
                    Order.setNote2(newOrder.getNote2());
                    return repository.save(Order);
                })
                .orElseGet(()->
                {
                    newOrder.setId(id);
                    return repository.save(newOrder);
                });
        EntityModel<Order> entityModel= assembler.toModel(updateOrder);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id)
    {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
