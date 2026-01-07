package com.icet.ordermanagementsystem.service.impl;

import com.icet.ordermanagementsystem.dto.ProductRequestDTO;
import com.icet.ordermanagementsystem.dto.ProductResponseDTO;
import com.icet.ordermanagementsystem.exception.ResourceNotFoundException;
import com.icet.ordermanagementsystem.model.Product;
import com.icet.ordermanagementsystem.repository.ProductRepository;
import com.icet.ordermanagementsystem.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product newProduct = new Product();
        newProduct.setName(dto.getName());
        newProduct.setDescription(dto.getDescription());
        newProduct.setPrice(dto.getPrice());
        newProduct.setStockQty(dto.getStockQty());

        Product savedProduct = productRepository.save(newProduct);
        return mapToResponseDTO(savedProduct);
    }

    private ProductResponseDTO mapToResponseDTO(Product savedProduct) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(savedProduct.getId());
        dto.setName(savedProduct.getName());
        dto.setDescription(savedProduct.getDescription());
        dto.setPrice(savedProduct.getPrice());
        dto.setStockQty(savedProduct.getStockQty());
        return dto;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Integer id) {
        Product exitingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Product not found with id: " + id)
                );
        return mapToResponseDTO(exitingProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO dto) {
        Product exitingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id)
                );

        exitingProduct.setName(dto.getName());
        exitingProduct.setDescription(dto.getDescription());
        exitingProduct.setPrice(dto.getPrice());
        exitingProduct.setStockQty(dto.getStockQty());

        Product savedProduct = productRepository.save(exitingProduct);
        return mapToResponseDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product exitingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id)
                );

        productRepository.delete(exitingProduct);
    }
}
