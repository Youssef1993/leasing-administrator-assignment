package com.leasing.administrator.leasingadministratorbackend.dtos;

import com.leasing.administrator.leasingadministratorbackend.entities.Vehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDTO {

    private Long id;
    @NotNull
    private String brand;
    private short modelYear;
    private String identificationNumber;
    private double price;

    @Builder
    public VehicleDTO(Long id, String brand, short modelYear, String identificationNumber, double price) {
        this.id = id;
        this.brand = brand;
        this.modelYear = modelYear;
        this.identificationNumber = identificationNumber;
        this.price = price;
    }

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.brand = vehicle.getBrand();
        this.modelYear = vehicle.getModelYear();
        this.identificationNumber = vehicle.getIdentificationNumber();
        this.price = vehicle.getPrice();
    }


    public Vehicle toEntity() {
        return Vehicle.builder()
                .id(this.id)
                .brand(this.brand)
                .modelYear(this.modelYear)
                .identificationNumber(this.identificationNumber)
                .price(this.price)
                .build();
    }
}
