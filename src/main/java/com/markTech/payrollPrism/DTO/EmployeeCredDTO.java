package com.markTech.payrollPrism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCredDTO
{
    long id;
    String firstName;
    String lastName;
    String email;

}
