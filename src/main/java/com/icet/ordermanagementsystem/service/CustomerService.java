package com.icet.ordermanagementsystem.service;

import com.icet.ordermanagementsystem.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer id);
    Customer updateCustomer(Integer id, Customer customer);
    void deleteCustomer(Integer id);
}
