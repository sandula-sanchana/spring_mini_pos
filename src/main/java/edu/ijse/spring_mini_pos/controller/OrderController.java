package edu.ijse.spring_mini_pos.controller;

import edu.ijse.spring_mini_pos.dto.OrderDTO;
import edu.ijse.spring_mini_pos.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/v1/order")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);
    }
}
