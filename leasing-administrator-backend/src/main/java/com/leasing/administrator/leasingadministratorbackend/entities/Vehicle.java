package com.leasing.administrator.leasingadministratorbackend.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand", nullable = false)
    private String brand;
    private short modelYear;
    private String identificationNumber;
    private double price;

    @Builder
    public Vehicle(Long id, String brand, short modelYear, String identificationNumber, double price) {
        this.id = id;
        this.brand = brand;
        this.modelYear = modelYear;
        this.identificationNumber = identificationNumber;
        this.price = price;
    }

    /**
     * Equals method here uses the id field for the lack of a natural identifier
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getId(), vehicle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
