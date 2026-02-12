package edu.ijse.spring_mini_pos.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    @Nullable
    private Integer cId;
    @NotBlank(message = "customer name is mandatory")
    private String cName;
    @NotBlank()
    @Size(min = 10)
    private String cAddress;
}
