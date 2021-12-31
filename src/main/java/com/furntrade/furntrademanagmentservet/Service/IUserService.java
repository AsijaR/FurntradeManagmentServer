package com.furntrade.furntrademanagmentservet.Service;

import com.furntrade.furntrademanagmentservet.Dtos.AccountDto;
import com.furntrade.furntrademanagmentservet.Dtos.EmployeeInfoDto;
import com.furntrade.furntrademanagmentservet.Dtos.UserChangePasswordDto;
import com.furntrade.furntrademanagmentservet.Models.AppRole;
import com.furntrade.furntrademanagmentservet.Models.AppUser;

import java.text.ParseException;
import java.util.List;

public interface IUserService {
    AccountDto saveUser(AccountDto newUser) throws ParseException;
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    EmployeeInfoDto getUser(String username);
    AppUser getUserApp(String username);
    List<EmployeeInfoDto> getUsers();
    void deleteUser(String username);
    boolean changePassword(String username,UserChangePasswordDto passwordDto);
    EmployeeInfoDto updateProfile(String username, EmployeeInfoDto userDto);
}
