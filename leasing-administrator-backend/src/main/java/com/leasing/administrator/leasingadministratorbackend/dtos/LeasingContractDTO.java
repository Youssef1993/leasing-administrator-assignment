package com.leasing.administrator.leasingadministratorbackend.dtos;

import com.leasing.administrator.leasingadministratorbackend.entities.Customer;
import com.leasing.administrator.leasingadministratorbackend.entities.LeasingContract;
import com.leasing.administrator.leasingadministratorbackend.entities.LeasingContractId;
import com.leasing.administrator.leasingadministratorbackend.entities.Vehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class LeasingContractDTO {

    private VehicleDTO vehicle;
    private CustomerDTO customer;
    @NotNull
    private String contractNumber;
    @NotNull
    private BigDecimal monthlyRate;

    @Builder
    public LeasingContractDTO(VehicleDTO vehicle, CustomerDTO customer, String contractNumber, BigDecimal monthlyRate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.contractNumber = contractNumber;
        this.monthlyRate = monthlyRate;
    }

    public LeasingContractDTO (LeasingContract leasingContract) {
        this.customer = Optional.ofNullable(leasingContract.getId())
                .map(LeasingContractId::getCustomer)
                .filter(Objects::nonNull)
                .map(CustomerDTO::new)
                .orElse(null);
        this.vehicle = Optional.ofNullable(leasingContract.getId())
                .map(LeasingContractId::getVehicle)
                .filter(Objects::nonNull)
                .map(VehicleDTO::new)
                .orElse(null);
        this.contractNumber = leasingContract.getContractNumber();
        this.monthlyRate = leasingContract.getMonthlyRate();
    }

    public LeasingContract toEntity() {
        Vehicle vehicle = Optional.ofNullable(this.vehicle).map(VehicleDTO::toEntity).orElse(null);
        Customer customer = Optional.ofNullable(this.customer).map(CustomerDTO::toEntity).orElse(null);
        LeasingContractId id = new LeasingContractId(vehicle, customer);
        return LeasingContract.builder()
                .id(id)
                .contractNumber(this.contractNumber)
                .monthlyRate(this.monthlyRate)
                .build();
    }
}
