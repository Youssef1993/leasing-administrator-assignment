package com.leasing.administrator.leasingadministratorbackend.repositories;

import com.leasing.administrator.leasingadministratorbackend.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("select v from Vehicle v where v.id not in (select c.id.vehicle.id from LeasingContract c)")
    List<Vehicle> findVehiclesWithoutContract();

    @Modifying
    @Query("update Vehicle v set v.identificationNumber = ?1 where v.id = ?2")
    void updateVehicleIdentificationNumber(String identificationNumber, Long vehicleId);
}
