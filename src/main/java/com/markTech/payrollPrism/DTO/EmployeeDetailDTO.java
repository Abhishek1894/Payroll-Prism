package com.markTech.payrollPrism.DTO;

import com.markTech.payrollPrism.model.Employee;
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

    private String contactNo;
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

    private long managerId;

    public EmployeeDetailDTO(Employee employee)
    {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();

        this.contactNo = employee.getContactNo();
        this.businessUnit = employee.getBusinessUnit();
        this.designation = employee.getDesignation();
        this.location = employee.getLocation();
        this.gender = employee.getGender();

        this.bankName = employee.getBankName();
        this.bankAccNo = employee.getBankAccNo();
        this.panNo = employee.getPanNo();
        this.esiNo = employee.getEsiNo();
        this.prnNo = employee.getPrnNo();

        this.managerId = employee.getEmployee() == null ? 0 : employee.getEmployee().getId();
    }
}
