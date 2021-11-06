package com.furntrade.furntrademanagmentservet.Repositories;

import com.furntrade.furntrademanagmentservet.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
