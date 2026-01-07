package com.icet.ordermanagementsystem.dto;

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
    private String status;
    private Double totalAmount;
    private Integer customerId;
    private List<OrderItemDTO> items;
}
