package com.leasing.administrator.leasingadministratorbackend.services;

import com.leasing.administrator.leasingadministratorbackend.entities.LeasingContract;
import com.leasing.administrator.leasingadministratorbackend.exceptions.InvalidDataException;
import com.leasing.administrator.leasingadministratorbackend.repositories.LeasingContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeasingContractService {

    private final LeasingContractRepository leasingContractRepository;
    private final VehicleService vehicleService;

    @Transactional
    public LeasingContract createLeasingContract(LeasingContract contract) {
        if (this.leasingContractRepository.vehicleExists(contract.getId().getVehicle().getId())) {
            throw new InvalidDataException("Vehicle Already in use");
        }
        return saveContract(contract);
    }

    public List<LeasingContract> getAllLeasingContracts() {
        return leasingContractRepository.findAll();
    }

    @Transactional
    public void editLeasingContract(LeasingContract contract) {
        if (this.leasingContractRepository.vehicleExists(contract.getId().getVehicle().getId())) {
            if (!this.leasingContractRepository.existsByVehicleAndCustomer(contract.getId().getVehicle().getId(), contract.getId().getCustomer().getId())) {
                throw new InvalidDataException("Vehicle Already in use by another contract");
            }
        }
        saveContract(contract);
    }

    private LeasingContract saveContract(LeasingContract contract) {
        LeasingContract savedContract = leasingContractRepository.save(contract);
        this.vehicleService.updateVehicleIdentificationNumber(contract.getId().getVehicle().getIdentificationNumber(), contract.getId().getVehicle().getId());
        return savedContract;
    }
}
