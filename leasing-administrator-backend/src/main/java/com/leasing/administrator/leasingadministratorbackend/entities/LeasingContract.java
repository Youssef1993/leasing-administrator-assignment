package com.leasing.administrator.leasingadministratorbackend.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LeasingContract {

    @EmbeddedId
    private LeasingContractId id;
    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;
    @Column(name = "monthly_rate", nullable = false)
    private BigDecimal monthlyRate;

    @Builder
    public LeasingContract(LeasingContractId id, String contractNumber, BigDecimal monthlyRate) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.monthlyRate = monthlyRate;
    }

    /**
     * the equals method here uses the contract number because it's a natural identifier, it can be used to compare objects
     * even without being saved in the database (not using the auto_generated id)
     * we use getters instead of directly using the objects so it can trigger lazy loading in Hibernate in case of current object being a proxy object.
     * while I avoided using the id field here because calling equals on the Customer and Vehicle objects will call the getId methods
     * on Vehicle and Customer Objects which will result in them being loaded from the database while they might not be needed.
     * @param o
     * @return true or false if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeasingContract that = (LeasingContract) o;
        return Objects.equals(getContractNumber(), that.getContractNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContractNumber());
    }
}
