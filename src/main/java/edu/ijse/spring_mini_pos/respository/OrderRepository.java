package edu.ijse.spring_mini_pos.respository;

import edu.ijse.spring_mini_pos.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, String> {
}
