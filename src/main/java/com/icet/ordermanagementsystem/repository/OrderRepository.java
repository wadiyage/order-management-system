package com.icet.ordermanagementsystem.repository;

import com.icet.ordermanagementsystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
