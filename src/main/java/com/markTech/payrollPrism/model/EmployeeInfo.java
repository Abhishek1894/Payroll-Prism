package com.markTech.payrollPrism.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Nullability;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo
{
    @Id
    private long id;

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

    private long reportingMangerId;


    public EmployeeInfo(long id)
    {
        this.id = id;
        this.reportingMangerId = 0;
    }
}
