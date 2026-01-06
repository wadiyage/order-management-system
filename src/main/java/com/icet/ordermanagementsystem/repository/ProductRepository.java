package com.icet.ordermanagementsystem.repository;

import com.icet.ordermanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
