package com.furntrade.furntrademanagmentservet.Dtos;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeInfoDto extends RepresentationModel<EmployeeInfoDto> {
   private String username;
   private String firstName;
   private String lastName;
   private String placeOfWork;
   private String email;
   private List<AppRoleDto> roles=new ArrayList<>();
}
