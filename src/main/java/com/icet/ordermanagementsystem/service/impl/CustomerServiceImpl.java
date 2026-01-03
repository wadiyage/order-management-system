package com.icet.ordermanagementsystem.service.impl;

import com.icet.ordermanagementsystem.model.Customer;
import com.icet.ordermanagementsystem.repository.CustomerRepository;
import com.icet.ordermanagementsystem.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: "+id));
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        Customer exitingCustomer = getCustomerById(id);

        exitingCustomer.setName(customer.getName());
        exitingCustomer.setEmail(customer.getEmail());
        exitingCustomer.setPhone(customer.getPhone());

        return customerRepository.save(exitingCustomer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer exitingCustomer = getCustomerById(id);
        customerRepository.delete(exitingCustomer);
    }
}
