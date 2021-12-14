package com.furntrade.furntrademanagmentservet.Repositories;
import com.furntrade.furntrademanagmentservet.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Order getOrderById(@Param("id") Long id);

}