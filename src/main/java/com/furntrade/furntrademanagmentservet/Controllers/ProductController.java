package com.furntrade.furntrademanagmentservet.Controllers;

import com.furntrade.furntrademanagmentservet.Exceptions.NotFoundExceptions.ObjectNotFoundException;
import com.furntrade.furntrademanagmentservet.ModelAssemblers.ProductModelAssembler;
import com.furntrade.furntrademanagmentservet.Models.Customer;
import com.furntrade.furntrademanagmentservet.Models.Product;
import com.furntrade.furntrademanagmentservet.Repositories.ProductRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;
    private final ProductModelAssembler assembler;

    public ProductController(ProductRepository repository, ProductModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    @GetMapping()
    public CollectionModel<EntityModel<Product>> All()
    {
        List<EntityModel<Product>> products = repository.findAll().stream().
                map(assembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(CustomerController.class).All()).withSelfRel());
    }
    @PostMapping("/add")
    ResponseEntity<?> newProduct(@RequestBody Product newProduct) {

        EntityModel<Product> entityModel = assembler.toModel(repository.save(newProduct));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Product> One(@PathVariable Long id)
    {
//        try{
//            Optional<Product> product=repository.findById(id);
//            return assembler.toModel(product);
//        }
//        catch (Exception ex)
//        {
//            ObjectNotFoundException error= new ObjectNotFoundException(id);
//            return
//        }
        Product product=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));

        return assembler.toModel(product);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> UpdateProduct(@RequestBody Product newProduct,@PathVariable Long id)
    {
        Product updateProduct=repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setColor(newProduct.getColor());
                    product.setModel(newProduct.getModel());
                    product.setMaterial(newProduct.getMaterial());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                })
                .orElseGet(()->
                {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        EntityModel<Product> entityModel= assembler.toModel(updateProduct);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
