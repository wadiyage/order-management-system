package com.icet.ordermanagementsystem.service.impl;

import com.icet.ordermanagementsystem.dto.CustomerRequestDTO;
import com.icet.ordermanagementsystem.dto.CustomerResponseDTO;
import com.icet.ordermanagementsystem.exception.ResourceNotFoundException;
import com.icet.ordermanagementsystem.model.Customer;
import com.icet.ordermanagementsystem.repository.CustomerRepository;
import com.icet.ordermanagementsystem.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {this.customerRepository = customerRepository; }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer newCustomer = new Customer();
        newCustomer.setName(dto.getName());
        newCustomer.setEmail(dto.getEmail());
        newCustomer.setPhone(dto.getPhone());


        Customer savedCustomer = customerRepository.save(newCustomer);
        return mapToResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(Integer id) {
        Customer exitingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return mapToResponse(exitingCustomer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Integer id, CustomerRequestDTO dto) {
        Customer exitingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        exitingCustomer.setName(dto.getName());
        exitingCustomer.setEmail(dto.getEmail());
        exitingCustomer.setPhone(dto.getPhone());

        return mapToResponse(customerRepository.save(exitingCustomer));
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    private CustomerResponseDTO mapToResponse(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
