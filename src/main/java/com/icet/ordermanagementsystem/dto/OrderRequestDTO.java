package com.icet.ordermanagementsystem.dto;

import com.icet.ordermanagementsystem.model.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

    @NotNull
    private Integer customerId;

    @NotNull
    private List<OrderItemDTO> items;
}
