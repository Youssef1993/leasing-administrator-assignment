package com.leasing.administrator.leasingadministratorbackend.dtos;

import com.leasing.administrator.leasingadministratorbackend.entities.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    @NotNull
    @Length(max = 255)
    private String firstName;
    @NotNull
    @Length(max = 255)
    private String lastName;
    @NotNull
    private LocalDate birthDate;

    @Builder
    public CustomerDTO(Long id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.birthDate = customer.getBirthDate();
    }


    public Customer toEntity() {
        return Customer.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .birthDate(this.birthDate)
                .build();
    }
}
