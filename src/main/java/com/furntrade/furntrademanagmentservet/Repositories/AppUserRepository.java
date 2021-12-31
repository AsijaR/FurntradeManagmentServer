package com.furntrade.furntrademanagmentservet.Repositories;

import com.furntrade.furntrademanagmentservet.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    Optional<AppUser> findById(Long id);
}
