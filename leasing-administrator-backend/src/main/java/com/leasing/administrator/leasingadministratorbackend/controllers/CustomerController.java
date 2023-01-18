package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.CustomerDTO;
import com.leasing.administrator.leasingadministratorbackend.entities.Customer;
import com.leasing.administrator.leasingadministratorbackend.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Creates a new Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created customer"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = this.customerService.createCustomer(customerDTO.toEntity());
        return new ResponseEntity<>(new CustomerDTO(customer), HttpStatus.CREATED);
    }

    @Operation(summary = "Returns List Of Customers")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = this.customerService.getAllCustomers();
        List<CustomerDTO> customerDTOS = customers.stream().map(CustomerDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @Operation(summary = "Update Existing Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Customer updated"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PutMapping
    public ResponseEntity<Void> editCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        this.customerService.editCustomer(customerDTO.toEntity());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
