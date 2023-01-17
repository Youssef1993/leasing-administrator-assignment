package com.leasing.administrator.leasingadministratorbackend.services;

import com.leasing.administrator.leasingadministratorbackend.entities.Vehicle;
import com.leasing.administrator.leasingadministratorbackend.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Transactional
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Transactional
    public void editVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
