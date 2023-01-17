package com.leasing.administrator.leasingadministratorbackend.controllers;


import com.leasing.administrator.leasingadministratorbackend.dtos.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void tetValidationForCustomerCreation_should_fail() {
        assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "/api/customers", new CustomerDTO(), CustomerDTO.class).getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void tetValidationForCustomerCreation_should_pass() {

        String customerFirstName = "Test Customer";
        String customerLastName = "Last name";
        LocalDate customerBirthDate = LocalDate.of(2000, 1, 1);
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName(customerFirstName)
                .lastName(customerLastName)
                .birthDate(customerBirthDate)
                .build();
        ResponseEntity<CustomerDTO> customerDTOResponseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/api/customers", customerDTO, CustomerDTO.class);
        assertThat(customerDTOResponseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(customerDTOResponseEntity.getBody()).isNotNull();
        assertThat(customerDTOResponseEntity.getBody().getId()).isNotNull();
        assertThat(customerDTOResponseEntity.getBody().getFirstName()).isEqualTo(customerFirstName);
        assertThat(customerDTOResponseEntity.getBody().getLastName()).isEqualTo(customerLastName);
        assertThat(customerDTOResponseEntity.getBody().getBirthDate()).isEqualTo(customerBirthDate);
    }


}
