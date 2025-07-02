package com.markTech.payrollPrism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCredDTO
{
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
