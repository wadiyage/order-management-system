package com.icet.ordermanagementsystem.controller;

import com.icet.ordermanagementsystem.dto.CustomerRequestDTO;
import com.icet.ordermanagementsystem.dto.CustomerResponseDTO;
import com.icet.ordermanagementsystem.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer API", description = "Operations related to customer management")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new customer", description = "Creates a new customer and stores in the database")
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO dto) {
        return customerService.createCustomer(dto);
    }

    @GetMapping
    @Operation(summary = "Get all exiting Customers", description = "Get all the customers, whose stored in the database")
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get existing any customer via Customer ID", description = "Get existing any customer, who stored in the database through its id")
    public CustomerResponseDTO getCustomerId(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update any existing customer details via Customer ID", description = "Update any exiting customer details, who stored in the database through its id")
    public CustomerResponseDTO updateCustomer(@PathVariable Integer id, @RequestBody CustomerRequestDTO dto) {
        return customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete any exiting customer via Customer ID", description = "Delete any exiting customer, who stored in the database through its id")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }
}
