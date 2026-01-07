package com.icet.ordermanagementsystem.dto;

import com.icet.ordermanagementsystem.model.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItem> items;
}
