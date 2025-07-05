package com.markTech.payrollPrism.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_gen")
    @SequenceGenerator(name = "employee_seq_gen", sequenceName = "employee_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean active;


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

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = true)
    Employee manager;

}
