package com.icet.ordermanagementsystem.dto;

import com.icet.ordermanagementsystem.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDTO {
    private Integer id;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Double totalAmount;
    private Integer customerId;
    private List<OrderItemDTO> items;
}
