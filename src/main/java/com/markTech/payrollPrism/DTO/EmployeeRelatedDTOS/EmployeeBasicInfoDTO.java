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
    byte[] profileImage;
    boolean active;

    public EmployeeBasicInfoDTO(Employee employee)
    {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.profileImage = employee.getProfileImage();
        this.active = employee.isActive();
    }

}
