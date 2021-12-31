package com.furntrade.furntrademanagmentservet.Dtos;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
@Data
public class AppRoleDto extends RepresentationModel<AppRoleDto> {
    private String name;
}
