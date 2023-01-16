package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.CustomerDTO;
import com.leasing.administrator.leasingadministratorbackend.entities.Customer;
import com.leasing.administrator.leasingadministratorbackend.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = this.customerService.createCustomer(customerDTO.toEntity());
        return new ResponseEntity<>(new CustomerDTO(customer), HttpStatus.CREATED);
    }

}
