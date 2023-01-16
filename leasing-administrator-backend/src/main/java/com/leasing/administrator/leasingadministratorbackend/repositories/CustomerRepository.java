package com.leasing.administrator.leasingadministratorbackend.repositories;

import com.leasing.administrator.leasingadministratorbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
