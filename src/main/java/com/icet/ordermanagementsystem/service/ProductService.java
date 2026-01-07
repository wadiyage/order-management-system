package com.icet.ordermanagementsystem.service;

import com.icet.ordermanagementsystem.dto.ProductRequestDTO;
import com.icet.ordermanagementsystem.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO dto);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Integer id);
    ProductResponseDTO updateProduct(Integer id, ProductRequestDTO dto);
    void deleteProduct(Integer id);
}
