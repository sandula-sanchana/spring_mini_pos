package edu.ijse.spring_mini_pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Orders {

    @Id
    private String orderId;

    private String customerId;
    private LocalDate orderDate;
    private double total;
}
