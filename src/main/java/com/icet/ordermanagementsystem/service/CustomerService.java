package com.icet.ordermanagementsystem.service;

import com.icet.ordermanagementsystem.dto.CustomerRequestDTO;
import com.icet.ordermanagementsystem.dto.CustomerResponseDTO;
import com.icet.ordermanagementsystem.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO getCustomerById(Integer id);
    CustomerResponseDTO updateCustomer(Integer id, CustomerRequestDTO dto);
    void deleteCustomer(Integer id);
}
