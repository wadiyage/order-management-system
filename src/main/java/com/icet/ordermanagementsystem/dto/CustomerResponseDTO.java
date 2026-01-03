package com.icet.ordermanagementsystem.dto;

public class CustomerResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;

    public CustomerResponseDTO(Integer id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
