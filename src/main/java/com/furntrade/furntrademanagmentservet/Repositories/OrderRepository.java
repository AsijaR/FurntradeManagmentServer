package com.furntrade.furntrademanagmentservet.Repositories;
import com.furntrade.furntrademanagmentservet.Models.Order;
import com.furntrade.furntrademanagmentservet.Models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Order getOrderById(Long id);
  Order findOrderById(Long id);
  List<Order> findAllByStatus(OrderStatus s);

}