package com.icet.ordermanagementsystem.service.impl;

import com.icet.ordermanagementsystem.dto.OrderItemDTO;
import com.icet.ordermanagementsystem.dto.OrderRequestDTO;
import com.icet.ordermanagementsystem.dto.OrderResponseDTO;
import com.icet.ordermanagementsystem.exception.ResourceNotFoundException;
import com.icet.ordermanagementsystem.model.Customer;
import com.icet.ordermanagementsystem.model.Order;
import com.icet.ordermanagementsystem.model.OrderItem;
import com.icet.ordermanagementsystem.repository.CustomerRepository;
import com.icet.ordermanagementsystem.repository.OrderRepository;
import com.icet.ordermanagementsystem.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Customer exitingCustomer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + dto.getCustomerId()
                ));

        Order newOrder = new Order();
        newOrder.setCustomer(exitingCustomer);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus("PENDING");

        List<OrderItem> items = dto.getItems().stream()
                .map(item -> mapToOrderItem(item, newOrder))
                .collect(Collectors.toList());

        double totalAmount = items.stream()
            .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();

        newOrder.setItems(items);
        newOrder.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(newOrder);
        return mapToOrderResponseDTO(savedOrder);
    }

    private OrderResponseDTO mapToOrderResponseDTO(Order savedOrder) {
        List<OrderItemDTO> items = savedOrder.getItems().stream()
                .map(item -> {
                    OrderItemDTO dto = new OrderItemDTO();
                    dto.setProduct(item.getProduct());
                    dto.setQty(item.getQty());
                    dto.setPrice(item.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());

        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setId(savedOrder.getId());
        responseDTO.setOrderDate(savedOrder.getOrderDate());
        responseDTO.setStatus(savedOrder.getStatus());
        responseDTO.setTotalAmount(savedOrder.getTotalAmount());
        responseDTO.setCustomerId(savedOrder.getCustomer().getId());
        responseDTO.setItems(items);

        return responseDTO;
    }

    private OrderItem mapToOrderItem(OrderItem item, Order newOrder) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(item.getProduct());
        orderItem.setQty(item.getQty());
        orderItem.setPrice(item.getPrice());
        orderItem.setOrder(newOrder);
        return item;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToOrderResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(Integer id) {
        Order exitingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id: " + id
                ));

        return mapToOrderResponseDTO(exitingOrder);
    }

    @Override
    public void deleteOrder(Integer id) {
        Order exitingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id: " + id
                ));

        orderRepository.delete(exitingOrder);
    }

}
