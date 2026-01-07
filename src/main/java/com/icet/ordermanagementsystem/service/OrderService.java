package com.icet.ordermanagementsystem.service;

import com.icet.ordermanagementsystem.dto.OrderRequestDTO;
import com.icet.ordermanagementsystem.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO dto);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(Integer id);
    void deleteOrder(Integer id);
}
