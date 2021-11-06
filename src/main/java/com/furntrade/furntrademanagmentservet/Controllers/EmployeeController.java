package com.furntrade.furntrademanagmentservet.Controllers;

import com.furntrade.furntrademanagmentservet.Exceptions.NotFoundExceptions.ObjectNotFoundException;
import com.furntrade.furntrademanagmentservet.ModelAssemblers.EmployeeModelAssembler;
import com.furntrade.furntrademanagmentservet.Models.AppUser;
import com.furntrade.furntrademanagmentservet.Repositories.AppUserRepository;
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
@RequestMapping("/employees")
public class EmployeeController {
    
    private final AppUserRepository repository;
    private final EmployeeModelAssembler assembler;

    public EmployeeController(AppUserRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<AppUser>> All()
    {
        List<EntityModel<AppUser>> AppUsers = repository.findAll().stream().
                map(assembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(AppUsers, linkTo(methodOn(EmployeeController.class).All()).withSelfRel());
    }
    @PostMapping("/add")
    ResponseEntity<?> newAppUser(@RequestBody AppUser newAppUser) {

        EntityModel<AppUser> entityModel = assembler.toModel(repository.save(newAppUser));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<AppUser> One(@PathVariable Long id)
    {
        AppUser appUser=repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));

        return assembler.toModel(appUser);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateAppUser(@RequestBody AppUser newAppUser,@PathVariable Long id)
    {
        AppUser updateAppUser=repository.findById(id)
                .map(AppUser -> {
                    AppUser.setFirstName(newAppUser.getFirstName());
                    AppUser.setLastName(newAppUser.getLastName());
                    AppUser.setPlaceOfWork(newAppUser.getPlaceOfWork());
                    AppUser.setEmail(newAppUser.getEmail());
                    return repository.save(AppUser);
                })
                .orElseGet(()->
                {
                    newAppUser.setId(id);
                    return repository.save(newAppUser);
                });
        EntityModel<AppUser> entityModel= assembler.toModel(updateAppUser);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAppUser(@PathVariable Long id)
    {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}