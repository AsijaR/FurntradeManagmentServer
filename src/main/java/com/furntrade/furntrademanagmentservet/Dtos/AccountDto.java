package com.furntrade.furntrademanagmentservet.Dtos;
import com.furntrade.furntrademanagmentservet.Models.AppRole;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountDto extends RepresentationModel<AccountDto>
    {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        List<AppRole> roles=new ArrayList<>();

    }
