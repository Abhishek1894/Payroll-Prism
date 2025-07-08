package com.markTech.payrollPrism.DTO.SalaryRelatedDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO
{
    String employee_id;
    String salaryDate;
    String salaryMonth;
    String salaryYear;
    String calenderDays;
    String paidDays;
    String arearDays;
    String overtimeDays;
    String overtimeRate;
    String lopDays;
    String basic;
    String hra;
    String conveyanceAllowance;
    String paymentsOvertime;
    String nightShiftAllowance;
    String specialAllowance;
    String deputationAllowance;
    String bonus;
    String otherAllowance;
    String arear;
    String grossNetTotal;
    String pf;
    String pt;
    String tds;
    String otherDeduction;
    String grossDeduction;
    String netTakeHome;
}
