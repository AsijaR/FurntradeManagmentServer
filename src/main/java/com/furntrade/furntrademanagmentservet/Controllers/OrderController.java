package com.furntrade.furntrademanagmentservet.Controllers;

import com.furntrade.furntrademanagmentservet.Dtos.OrderDetailsDto;
import com.furntrade.furntrademanagmentservet.Dtos.OrderResponse;
import com.furntrade.furntrademanagmentservet.Dtos.OrdersDto;
import com.furntrade.furntrademanagmentservet.Exceptions.NotFoundExceptions.ObjectNotFoundException;
import com.furntrade.furntrademanagmentservet.ModelAssemblers.OrderModelAssembler;
import com.furntrade.furntrademanagmentservet.Models.Order;
import com.furntrade.furntrademanagmentservet.Models.OrderStatus;
import com.furntrade.furntrademanagmentservet.Models.Product;
import com.furntrade.furntrademanagmentservet.Models.ProductOrderDetails;
import com.furntrade.furntrademanagmentservet.Repositories.OrderRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.EnumUtils;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

//import static com.furntrade.furntrademanagmentservet.Models.OrderStatus.getOrderStatus;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderRepository repository;
    private final OrderModelAssembler assembler;

    @Autowired
    private ModelMapper modelMapper;

    public OrderController(OrderRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        //modelMapper.typeMap(OrderDetailsDto.class, OrderDetailsDto.class).addMapping(Pro::getQuantity, OrderDetailsDto::set);
    }
    @GetMapping()
    public CollectionModel<OrdersDto> All()
    {
        List<OrdersDto> orders = repository.findAll().stream().
                map(assembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(orders);
    }
    @GetMapping("/p")
    public Collection<OrdersDto> frf()
    {
        var o = repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return o;
    }
    @PostMapping("/add")
    ResponseEntity<?> newOrder(@RequestBody OrdersDto newOrder) throws ParseException {

        Order o =convertToEntity(newOrder);
        OrdersDto entityModel = assembler.toModel(repository.save(o));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public Order One(@PathVariable Long id)
    {
        Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
        Order pod=  repository.getById(id);
       // ModelMapper modelMapper = new ModelMapper();
        //OrderDetailsDto ordersDetailsDto = modelMapper.map(order, OrderDetailsDto.class);
        return pod;
    }
    @PatchMapping("/change-status/{id}")
    ResponseEntity<?> updateOrderStatus(@RequestBody OrderStatus status, @PathVariable Long id)
    {
       Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
        try {

            var isOrderStatusValid =EnumUtils.isValidEnum(OrderStatus.class, status.getDepCode());
            order.setStatus(status);
            return ResponseEntity.ok(assembler.toModel(repository.save(order)));

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Not valid value of status");
        }
    }
    @PutMapping("/update/{id}")
    ResponseEntity<?> updateOrder(@RequestBody OrdersDto newOrder, @PathVariable Long id) throws ParseException {
//        Order updateOrder=repository.findById(id)
//                .map(Order -> {
////                    Order.setCustomer(newOrder.);
////                    Order.setShippmentDate(newOrder.getShippmentDate());
////                    Order.setNote1(newOrder.getNote1());
////                    Order.setNote2(newOrder.getNote2());
////                    return repository.save(Order);
//                })
//                .orElseGet(()->
//                {
//                    newOrder.setId(id);
//                    return repository.save(newOrder);
//                });

        Order o =convertToEntity(newOrder);
        return  ResponseEntity.ok(assembler.toModel(o));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id)
    {
        var o =repository.findOrderById(id);
      //  var c=repository.findByOrderId(id);
      //  o.getProductOrderDetails().remove(this);
      //  o.reo
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private OrdersDto convertToDto(Order order) {
        OrdersDto ordersDto = modelMapper.map(order, OrdersDto.class);
        return ordersDto;
    }

    private Order convertToEntity(OrdersDto ordersDto) throws ParseException {
        Order order = modelMapper.map(ordersDto, Order.class);
        if (ordersDto.getId() != null) {
            Order oldOrder = repository.getById(ordersDto.getId());
            //post.setre(oldOrder.getRedditID());
           // post.setSent(oldOrder.isSent());
        }
        return order;
    }
}
