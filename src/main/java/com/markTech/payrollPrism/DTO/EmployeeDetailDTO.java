package com.markTech.payrollPrism.DTO;

import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.model.EmployeeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO
{
    long id;

    String firstName;
    String lastName;
    String email;

    private String businessUnit;
    private String designation;
    private String location;
    private String gender;

    private Date dateOfBirth;
    private Date dateOfJoining;

    private String bankName;
    private String bankAccNo;
    private String panNo;
    private String esiNo;
    private String prnNo;

    public EmployeeDetailDTO(Employee employee, EmployeeInfo employeeInfo)
    {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();

        this.businessUnit = employeeInfo.getBusinessUnit();
        this.designation = employeeInfo.getDesignation();
        this.location = employeeInfo.getLocation();
        this.gender = employeeInfo.getGender();

        this.bankName = employeeInfo.getBankName();
        this.bankAccNo = employeeInfo.getBankAccNo();
        this.panNo = employeeInfo.getPanNo();
        this.esiNo = employeeInfo.getEsiNo();
        this.prnNo = employeeInfo.getPrnNo();
    }
}
