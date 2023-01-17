package com.leasing.administrator.leasingadministratorbackend.repositories;

import com.leasing.administrator.leasingadministratorbackend.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
