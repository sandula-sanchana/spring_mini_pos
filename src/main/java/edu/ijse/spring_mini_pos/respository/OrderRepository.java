package edu.ijse.spring_mini_pos.respository;

import edu.ijse.spring_mini_pos.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT MAX(o.orderId) FROM Orders o")
    Integer findMaxOrderId();

}
