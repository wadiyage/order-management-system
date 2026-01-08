package com.icet.ordermanagementsystem.controller;

import com.icet.ordermanagementsystem.dto.ProductRequestDTO;
import com.icet.ordermanagementsystem.dto.ProductResponseDTO;
import com.icet.ordermanagementsystem.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Operations related to product management")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product", description = "Creates a new product and stores in the database")
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping
    @Operation(summary = "Get all exiting products", description = "Get all the products, those stored in the database")
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get existing any product via Product ID", description = "Get existing any product, that stored in the database through its id")
    public ProductResponseDTO getProductId(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update any existing product details via Product ID", description = "Update any exiting product details, that stored in the database through its id")
    public ProductResponseDTO updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDTO dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete any exiting product via Product ID", description = "Delete any exiting product, that stored in the database through its id")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
