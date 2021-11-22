package com.furntrade.furntrademanagmentservet.Repositories;

import com.furntrade.furntrademanagmentservet.Dtos.OrderResponse;
import com.furntrade.furntrademanagmentservet.Models.Order;
import com.furntrade.furntrademanagmentservet.Models.ProductOrderDetails;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

   // Optional<Order> findByEmail(String email);
  //  @Query("SELECT v FROM Vote v where v.user.id = :userId and v.poll.id = :pollId")
  //  @Query("SELECT o.id, o.shippmentDate, o.status, o.note1,o.note2,c.name from Order o inner join Customer c on Order.customer.id= c.id")
  // @Transactional
  // @Modifying
  // @Query(value = "SELECT o.id,c.name, o.shippmentDate, o.status, o.note1,o.note2 from Order o INNER JOIN Customer c ON o.customer.id= c.id",nativeQuery = true)
   @Query(value = "SELECT o.id, o.shippment_date, o.status, o.note1,o.note2,o.total_order_price from Orders o LEFT OUTER JOIN ProductOrderDetails c ON o.id= c.order_id ",nativeQuery = true)
   Collection<Order> includeProducts();

  // @Query("select o.status from Order o join o.productOrderDetails p where o.id=p.order.id")
   //@Param("id") int id
  @Query("SELECT o FROM Order o INNER JOIN Customer c where o.customer.id=c.id")
   Order getOrderDetails();

  Order findOrderById(Long id);

  //  @Query("SELECT new com.javatechie.jpa.dto.OrderResponse(c.status , p.productName) FROM Order c JOIN c.product_order_details p")
 //   Collection<OrderResponse> getOrderDet();
}