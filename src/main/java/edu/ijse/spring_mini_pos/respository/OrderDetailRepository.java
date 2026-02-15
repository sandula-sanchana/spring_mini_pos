package edu.ijse.spring_mini_pos.respository;

import edu.ijse.spring_mini_pos.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrder_OrderId(Integer orderId);
}
