package edu.ijse.spring_mini_pos.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private Integer customerId;
    private double total;
    private List<OrderDetailDTO> items;
}
