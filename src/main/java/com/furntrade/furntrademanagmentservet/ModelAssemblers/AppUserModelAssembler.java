package com.furntrade.furntrademanagmentservet.ModelAssemblers;
import com.furntrade.furntrademanagmentservet.Controllers.AppUserController;
import com.furntrade.furntrademanagmentservet.Dtos.AccountDto;
import com.furntrade.furntrademanagmentservet.Dtos.EmployeeInfoDto;
import com.furntrade.furntrademanagmentservet.Models.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class AppUserModelAssembler implements RepresentationModelAssembler<AppUser,EmployeeInfoDto>{

    @Override
    public EmployeeInfoDto toModel(AppUser user) {
        ModelMapper modelMapper = new ModelMapper();
        EmployeeInfoDto usersDto = modelMapper.map(user, EmployeeInfoDto.class);
        Link selfLink = linkTo(methodOn(AppUserController.class).One(user.getUsername())).withSelfRel();
        usersDto.add(selfLink);
        return usersDto;
    }
    public AppUser convertToAppUserEntity(AccountDto usersDto) throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        AppUser user = modelMapper.map(usersDto, AppUser.class);
        return user;
    }

    @Override
    public CollectionModel<EmployeeInfoDto> toCollectionModel(Iterable<? extends AppUser> userList) {
        ModelMapper modelMapper = new ModelMapper();
        List<EmployeeInfoDto> usersDTOS = new ArrayList<>();

        for (AppUser user : userList){
            EmployeeInfoDto ordersDto = modelMapper.map(user, EmployeeInfoDto.class);
            ordersDto.add(linkTo(methodOn(AppUserController.class).One(user.getUsername())).withSelfRel());
            usersDTOS.add(ordersDto);
        }

        return new CollectionModel<EmployeeInfoDto>(usersDTOS);
    }
//    @Override
//    public EntityModel<AppUser> toModel(AppUser employee) {
//        return EntityModel.of(employee, //
//                linkTo(methodOn(AppUserController.class).One(employee.getUsername())).withSelfRel());
//               // linkTo(methodOn(AppUserController.class).All()).withRel("employees"));
//
//    }
}