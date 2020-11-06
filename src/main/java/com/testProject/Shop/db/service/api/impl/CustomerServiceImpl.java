package com.testProject.Shop.db.service.api.impl;

import com.testProject.Shop.db.repository.CustomerRepository;
import com.testProject.Shop.db.service.api.CustomerService;
import com.testProject.Shop.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

//tu sa implementuje logika

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getAll();
    }

    @Override
    public Customer get(int id) {
        return customerRepository.get(id);
    }

    @Override
    public Integer add(Customer customer) {
        return customerRepository.add(customer);
    }
}
