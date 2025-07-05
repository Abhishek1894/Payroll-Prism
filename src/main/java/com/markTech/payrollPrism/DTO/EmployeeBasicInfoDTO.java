package com.markTech.payrollPrism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class EmployeeBasicInfoDTO
{
    long id;
    String firstName;
    String lastName;
    String email;

    public EmployeeBasicInfoDTO(long id, String firstName, String lastName, String email)
    {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }
}
