package com.icet.ordermanagementsystem.dto;

import com.icet.ordermanagementsystem.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderItemDTO {
    @NotBlank(message = "Product name is required")
    private Product product;

    @NotNull
    @Min(value = 1, message = "Quantity must be al least 1")
    private Integer qty;

    @NotNull
    @Min(value = 0, message = "Price must be non-negative")
    private Double price;
}
