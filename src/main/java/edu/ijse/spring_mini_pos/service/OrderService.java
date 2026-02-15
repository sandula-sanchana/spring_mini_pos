package edu.ijse.spring_mini_pos.service;

import edu.ijse.spring_mini_pos.dto.OrderDTO;

public interface OrderService {
    Integer placeOrder(OrderDTO orderDTO);
    Integer getNextOrderId();

}
