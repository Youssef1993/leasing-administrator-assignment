package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.VehicleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerIntegrationTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void tetValidationForVehicleCreation_should_fail() {
        assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "/api/vehicles", new VehicleDTO(), VehicleDTO.class).getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void tetValidationForVehicleCreation_should_pass() {

        String brand = "TBrand";
        short modelYear =1850;
        String identificationNumber = null;
        double price = 25_000;
        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .brand(brand)
                .modelYear(modelYear)
                .price(price)
                .identificationNumber(identificationNumber)
                .build();
        ResponseEntity<VehicleDTO> vehicleDTOResponseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/api/vehicles", vehicleDTO, VehicleDTO.class);
        assertThat(vehicleDTOResponseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(vehicleDTOResponseEntity.getBody()).isNotNull();
        assertThat(vehicleDTOResponseEntity.getBody().getId()).isNotNull();
        assertThat(vehicleDTOResponseEntity.getBody().getBrand()).isEqualTo(brand);
        assertThat(vehicleDTOResponseEntity.getBody().getPrice()).isEqualTo(price);
        assertThat(vehicleDTOResponseEntity.getBody().getModelYear()).isEqualTo(modelYear);
        assertThat(vehicleDTOResponseEntity.getBody().getIdentificationNumber()).isNull();
    }
}
