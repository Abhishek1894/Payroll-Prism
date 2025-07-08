package com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS;

import com.markTech.payrollPrism.model.Employee;
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

    public EmployeeBasicInfoDTO(Employee employee)
    {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
    }

    public EmployeeBasicInfoDTO(long id, String firstName, String lastName, String email)
    {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }
}
