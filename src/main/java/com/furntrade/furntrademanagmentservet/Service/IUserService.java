package com.furntrade.furntrademanagmentservet.Service;

import com.furntrade.furntrademanagmentservet.Models.AppRole;
import com.furntrade.furntrademanagmentservet.Models.AppUser;

import java.util.List;

public interface IUserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
