package com.furntrade.furntrademanagmentservet.Controllers;

import com.furntrade.furntrademanagmentservet.Dtos.*;
import com.furntrade.furntrademanagmentservet.Exceptions.NotFoundExceptions.ObjectNotFoundException;
import com.furntrade.furntrademanagmentservet.ModelAssemblers.OrderModelAssembler;
import com.furntrade.furntrademanagmentservet.Models.*;
import com.furntrade.furntrademanagmentservet.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository repository;
    private final OrderModelAssembler assembler;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderController(OrderRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
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
    public OrdersDto SearchById(@RequestParam Long id)
    {
        var o = repository.getOrderById(id);
        return assembler.toModel(o);
    }
    @GetMapping("/filter-order-status")
    public CollectionModel<OrdersDto> FilterOrderByStatus(@RequestParam String status)
    {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        List<OrdersDto> orders = repository.findAllByStatus(orderStatus).stream().
                map(assembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(orders);
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
    ResponseEntity<?> newOrder(@RequestBody OrderRequestDto orderRequestDto) throws ParseException {
        Order newOrder=convertToOrderEntity(orderRequestDto.getOrder());
        Customer customer=customerRepository.findByName(orderRequestDto.getOrder().getCustomerName());
        newOrder.setCustomer(customer);
        newOrder.setStatus(OrderStatus.WAITING);
        repository.save(newOrder);
        for (ProductOrderDetailsDto prod: orderRequestDto.getProducts()) {
            newOrder.addProduct(prod.getProduct(),prod.getQuantity());
        }
        try{
            repository.save(newOrder);
            return ResponseEntity.ok(convertToDto(newOrder));
        }
        catch (Exception ex)
        {
            return ResponseEntity.ok("You cant add two same products to the order");
        }
    }
    @PatchMapping("{id}/remove-product/{productId}")
    ResponseEntity<?> removeProductFromOrder(@PathVariable Long id,@PathVariable Long productId,@RequestParam int quantity) throws ParseException
    {
        Order findOrder=repository.findOrderById(id);
        Product product=productRepository.getById(productId);
        var productExistInOrder=findOrder.getOrderedProducts().stream().anyMatch(p->p.getProduct()==product);
        if(productExistInOrder && findOrder.getOrderedProducts().size()>1)
        {
            findOrder.removeProduct(product,quantity);
            repository.save(findOrder);
            return ResponseEntity.ok("Product is removed from the order");
        }
       else
       {
           return (ResponseEntity<?>) ResponseEntity.badRequest();
       }
    }
    @PatchMapping("{id}/product/{productId}/change-quantity/")
    ResponseEntity<?> changeProductQuantity(@PathVariable Long id,@PathVariable Long productId,@RequestParam int quantity) throws ParseException
    {
        Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
        Product product=productRepository.findById(productId).orElseThrow(()->new ObjectNotFoundException(id));
        var ddd=order.getOrderedProducts().stream().filter(p->p.getProduct()==product);
        var productExistInOrder=order.getOrderedProducts().stream().anyMatch(p->p.getProduct()==product);
        if(productExistInOrder)
        {
           // var c=repository.update(order,product,quantity);
            order.addProduct(product,quantity);
            try {
           //     repository.save(order);
                return ResponseEntity.accepted().body(order);
            }
            catch (Exception ex)
            {
                return ResponseEntity.ok("Product is already part of the order. Cant add duplicates!");
            }
        }
        else
        {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }
    @PatchMapping("{id}/add-product/{productId}")
    ResponseEntity<?> addProductToOrder(@PathVariable Long id,@PathVariable Long productId,@RequestParam int quantity) throws ParseException
    {
        Order order=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
        Product product=productRepository.findById(productId).orElseThrow(()->new ObjectNotFoundException(id));

        var productExistInOrder=order.getOrderedProducts().stream().anyMatch(p->p.getProduct()==product);
        if(!productExistInOrder)
        {
            order.addProduct(product,quantity);
            try {
                repository.save(order);
                return ResponseEntity.accepted().body(order);
            }
           catch (Exception ex)
           {
               return ResponseEntity.ok("Product is already part of the order. Cant add duplicates!");
           }
        }
        else
        {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @PostMapping("/list/{id}")
//    @Consumes({ MediaType.APPLICATION_JSON })
//    @Produces({ MediaType.APPLICATION_JSON })
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
    ResponseEntity<?> updateOrder(@PathVariable Long id,@RequestBody OrdersDto newOrder) throws ParseException {
        Customer customer=customerRepository.findByName(newOrder.getCustomerName());
        Order updateOrder=repository.findById(id)
                .map(order -> {
                    order.setCustomer(customer);
                    order.setShippmentDate(newOrder.getShippmentDate());
                    order.setNote1(newOrder.getNote1());
                    order.setNote2(newOrder.getNote2());
                    order.setStatus(newOrder.getStatus());
                    return repository.save(order);
                }).orElseThrow(()->new ObjectNotFoundException(id));
        var entityModel= assembler.toModel(updateOrder);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
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

    private Order convertToOrderEntity(OrdersDto ordersDto) throws ParseException {
        Order order = modelMapper.map(ordersDto, Order.class);
        if (ordersDto.getId() != null) {
            Order oldOrder = repository.getById(ordersDto.getId());
        }
        return order;
    }
}
