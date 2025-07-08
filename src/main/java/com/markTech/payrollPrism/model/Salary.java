package com.markTech.payrollPrism.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.Year;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary
{
    @Id
    String id;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    Employee employee;

    @Column(nullable = false)
    String salaryDate;

    @Column(nullable = false)
    String salaryMonth;

    @Column(nullable = false)
    int salaryYear;

    @Column(nullable = false)
    double calenderDays;

    @Column(nullable = false)
    double paidDays;

    @Column(nullable = false)
    double arearDays;

    @Column(nullable = false)
    double overtimeDays;

    @Column(nullable = false)
    double overtimeRate;

    @Column(nullable = false)
    double lopDays;

    @Column(nullable = false)
    double basic;

    @Column(nullable = false)
    double hra;

    @Column(nullable = false)
    double conveyanceAllowance;

    @Column(nullable = false)
    double paymentsOvertime;

    @Column(nullable = false)
    double nightShiftAllowance;

    @Column(nullable = false)
    double specialAllowance;

    @Column(nullable = false)
    double deputationAllowance;

    @Column(nullable = false)
    double bonus;

    @Column(nullable = false)
    double otherAllowance;

    @Column(nullable = false)
    double arear;

    @Column(nullable = false)
    double grossNetTotal;

    @Column(nullable = false)
    double pf;

    @Column(nullable = false)
    double pt;

    @Column(nullable = false)
    double tds;

    @Column(nullable = false)
    double otherDeduction;

    @Column(nullable = false)
    double grossDeduction;

    @Column(nullable = false)
    double netTakeHome;

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", employee=" + employee +
                ", salaryDate=" + salaryDate +
                ", salaryMonth=" + salaryMonth +
                ", salaryYear=" + salaryYear +
                ", calenderDays=" + calenderDays +
                ", paidDays=" + paidDays +
                ", arearDays=" + arearDays +
                ", overtimeDays=" + overtimeDays +
                ", overtimeRate=" + overtimeRate +
                ", lopDays=" + lopDays +
                ", basic=" + basic +
                ", hra=" + hra +
                ", conveyanceAllowance=" + conveyanceAllowance +
                ", paymentsOvertime=" + paymentsOvertime +
                ", nightShiftAllowance=" + nightShiftAllowance +
                ", specialAllowance=" + specialAllowance +
                ", deputationAllowance=" + deputationAllowance +
                ", bonus=" + bonus +
                ", otherAllowance=" + otherAllowance +
                ", arear=" + arear +
                ", grossNetTotal=" + grossNetTotal +
                ", pf=" + pf +
                ", pt=" + pt +
                ", tds=" + tds +
                ", otherDeduction=" + otherDeduction +
                ", grossDeduction=" + grossDeduction +
                ", netTakeHome=" + netTakeHome +
                '}';
    }
}
