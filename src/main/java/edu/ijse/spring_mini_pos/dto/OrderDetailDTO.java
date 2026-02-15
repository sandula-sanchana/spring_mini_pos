package edu.ijse.spring_mini_pos.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderDetailDTO {
    private Integer itemId;
    private int qty;
    private double unitPrice;
}
