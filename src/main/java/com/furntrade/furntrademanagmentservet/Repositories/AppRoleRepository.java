package com.furntrade.furntrademanagmentservet.Repositories;

import com.furntrade.furntrademanagmentservet.Models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}
