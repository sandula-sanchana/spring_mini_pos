package edu.ijse.spring_mini_pos.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderDTO {
    private String orderId;
    private String customerId;
    private double total;
    private List<OrderDetailDTO> items;
}
