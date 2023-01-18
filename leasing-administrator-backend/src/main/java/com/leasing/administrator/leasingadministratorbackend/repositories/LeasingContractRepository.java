package com.leasing.administrator.leasingadministratorbackend.repositories;

import com.leasing.administrator.leasingadministratorbackend.entities.LeasingContract;
import com.leasing.administrator.leasingadministratorbackend.entities.LeasingContractId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeasingContractRepository extends JpaRepository<LeasingContract, LeasingContractId> {

    @Query("select c from LeasingContract c join fetch c.id.vehicle join fetch c.id.customer")
    List<LeasingContract> findAll();

    @Query("select case when count(c) > 0 then true else false end from LeasingContract c where c.id.vehicle.id = ?1")
    boolean vehicleExists(Long vehicleId);

    @Query("select case when count(c) > 0 then true else false end from LeasingContract c where c.id.vehicle.id = ?1 and c.id.customer.id = ?2")
    boolean existsByVehicleAndCustomer(Long vehicleId, Long customerId);
}
