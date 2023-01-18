package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.CustomerDTO;
import com.leasing.administrator.leasingadministratorbackend.dtos.LeasingContractDTO;
import com.leasing.administrator.leasingadministratorbackend.dtos.VehicleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeasingContractControllerIntegrationTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testFailOnDuplicateVehicleOnCreation() {
        VehicleDTO vehicleDTO = VehicleDTO.builder().brand("BRAND").price(10_000).modelYear((short) 2018).build();
        ResponseEntity<VehicleDTO> vehicleResponse = this.restTemplate.postForEntity("http://localhost:" + port + "/api/vehicles", vehicleDTO, VehicleDTO.class);
        assertThat(vehicleResponse.getBody()).isNotNull();
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("First NAme").lastName("last name").birthDate(LocalDate.of(2000, 1, 1)).build();
        ResponseEntity<CustomerDTO> customerResponse = this.restTemplate.postForEntity("http://localhost:" + port + "/api/customers", customerDTO, CustomerDTO.class);
        assertThat(customerResponse.getBody()).isNotNull();
        LeasingContractDTO contractDTO = LeasingContractDTO.builder().contractNumber("012").vehicle(vehicleResponse.getBody()).customer(customerResponse.getBody()).monthlyRate(BigDecimal.valueOf(255)).build();
        ResponseEntity<LeasingContractDTO> contractResponse = this.restTemplate.postForEntity("http://localhost:" + port + "/api/leasing-contracts", contractDTO, LeasingContractDTO.class);
        assertThat(contractResponse.getBody()).isNotNull();
        assertThat(contractResponse.getStatusCodeValue()).isEqualTo(201);
        ResponseEntity<com.leasing.administrator.leasingadministratorbackend.dtos.Value> contractErrorResponse = this.restTemplate.postForEntity("http://localhost:" + port + "/api/leasing-contracts", contractDTO, com.leasing.administrator.leasingadministratorbackend.dtos.Value.class);
        assertThat(contractErrorResponse.getStatusCodeValue()).isEqualTo(400);
        assertThat(contractErrorResponse.getBody().getValue()).isEqualTo("Vehicle Already in use");
    }

}
