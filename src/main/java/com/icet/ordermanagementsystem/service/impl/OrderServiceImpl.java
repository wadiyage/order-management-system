package com.icet.ordermanagementsystem.service.impl;

import com.icet.ordermanagementsystem.dto.OrderItemDTO;
import com.icet.ordermanagementsystem.dto.OrderRequestDTO;
import com.icet.ordermanagementsystem.dto.OrderResponseDTO;
import com.icet.ordermanagementsystem.exception.ResourceNotFoundException;
import com.icet.ordermanagementsystem.model.*;
import com.icet.ordermanagementsystem.repository.CustomerRepository;
import com.icet.ordermanagementsystem.repository.OrderRepository;
import com.icet.ordermanagementsystem.repository.ProductRepository;
import com.icet.ordermanagementsystem.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Customer exitingCustomer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + dto.getCustomerId()
                ));

        Order newOrder = new Order();
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus(OrderStatus.CREATED);
        newOrder.setCustomer(exitingCustomer);

        List<OrderItem> items = dto.getItems().stream()
                .map(itemDto -> mapToOrderItem(itemDto, newOrder))
                .collect(Collectors.toList());

        newOrder.setItems(items);

        double totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();

        newOrder.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(newOrder);
        return mapToOrderResponseDTO(savedOrder);
    }

    private OrderResponseDTO mapToOrderResponseDTO(Order savedOrder) {
        List<OrderItemDTO> items = savedOrder.getItems().stream()
                .map(item -> {
                    OrderItemDTO dto = new OrderItemDTO();
                    dto.setProductName(item.getProduct().getName());
                    dto.setQty(item.getQty());
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

    private OrderItem mapToOrderItem(OrderItemDTO dto, Order newOrder) {
        Product exitingProduct = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + dto.getProductId())
                );

        if(exitingProduct.getStockQty()<dto.getQty()) {
            throw new IllegalArgumentException(
                    "Insufficient stock for product: " + exitingProduct.getName()
            );
        }

        exitingProduct.setStockQty(exitingProduct.getStockQty() - dto.getQty());
        productRepository.save(exitingProduct);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(exitingProduct);
        orderItem.setQty(dto.getQty());
        orderItem.setPrice(exitingProduct.getPrice());
        orderItem.setOrder(newOrder);

        return orderItem;
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
