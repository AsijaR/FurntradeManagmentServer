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
import org.aspectj.weaver.ast.Or;
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
    @GetMapping("/search")
    public Order SearchById(@RequestParam Long id)
    {
        var o = repository.getOrderById(id);
        return o;
    }
    @GetMapping("/filter-order-status")
    public List<Order> FilterOrderByStatus(@RequestParam String status)
    {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        var o = repository.findAllByStatus(orderStatus);
        return o;
    }
    @GetMapping("/{id}")
    public Order One(@PathVariable Long id)
    {
        Order order=repository.getOrderById(id);
        //Order pod=  repository.getById(id);
        // ModelMapper modelMapper = new ModelMapper();
        //OrderDetailsDto ordersDetailsDto = modelMapper.map(order, OrderDetailsDto.class);
        return order;
    }
    @PatchMapping("/change-status/{id}")
    ResponseEntity<?> updateOrderStatus(@RequestParam String status, @PathVariable Long id)
    {
        Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
        try {

            OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(orderStatus);
            return ResponseEntity.ok(assembler.toModel(repository.save(order)));

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Not valid value of status");
        }
    }
    @PostMapping("/add")
    ResponseEntity<?> newOrder(@RequestBody Order newOrder) throws ParseException {

       // Order o =convertToEntity(newOrder);
//        newOrder.
//        OrdersDto entityModel = assembler.toModel(repository.save(newOrder));
//
//        return ResponseEntity //
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//                .body(entityModel);
        return  ResponseEntity.ok("");
    }

    @PostMapping("/list/{id}")
    ResponseEntity<?> listProd(@RequestBody List<Product> products,@PathVariable Long id) throws ParseException {
        Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
        try {

            for(Product product:products) {
                order.addProduct(product,4);
            }
            repository.save(order);
            return ResponseEntity.ok(assembler.toModel(order));}
        catch (IllegalArgumentException ex) {
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
