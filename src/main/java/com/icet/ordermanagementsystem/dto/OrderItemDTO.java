package com.icet.ordermanagementsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    @NotNull
    private Integer productId;

    private String productName;

    @NotNull
    @Min(1)
    private Integer qty;
}
