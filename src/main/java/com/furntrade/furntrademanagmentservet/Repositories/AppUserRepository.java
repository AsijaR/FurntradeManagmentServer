package com.furntrade.furntrademanagmentservet.Repositories;

import com.furntrade.furntrademanagmentservet.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
