package com.icet.ordermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQty;
}
