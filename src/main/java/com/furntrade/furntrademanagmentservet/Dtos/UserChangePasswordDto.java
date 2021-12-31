package com.furntrade.furntrademanagmentservet.Dtos;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserChangePasswordDto extends RepresentationModel<UserChangePasswordDto> {
    private String oldPassword;
    private String newPassword;
}