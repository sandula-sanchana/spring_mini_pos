package edu.ijse.spring_mini_pos.controller;

import edu.ijse.spring_mini_pos.dto.OrderDTO;
import edu.ijse.spring_mini_pos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/v1/order")
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer placeOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.placeOrder(orderDTO);
    }

    @GetMapping("/next-id")
    public Integer nextOrderId() {
        return orderService.getNextOrderId();
    }
}
