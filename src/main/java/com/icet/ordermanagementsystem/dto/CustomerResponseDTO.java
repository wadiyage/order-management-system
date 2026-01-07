package com.icet.ordermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;

}
