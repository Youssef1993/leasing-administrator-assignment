package com.leasing.administrator.leasingadministratorbackend.services;

import com.leasing.administrator.leasingadministratorbackend.entities.Customer;
import com.leasing.administrator.leasingadministratorbackend.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
