package com.markTech.payrollPrism.DTO.SalaryRelatedDTOS;
import com.markTech.payrollPrism.model.Salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryResponseDTO
{
    Long employee_id;
    String salaryDate;
    String salaryMonth;
    int salaryYear;
    double calenderDays;
    double paidDays;
    double arearDays;
    double overtimeDays;
    double overtimeRate;
    double lopDays;
    double basic;
    double hra;
    double conveyanceAllowance;
    double paymentsOvertime;
    double nightShiftAllowance;
    double specialAllowance;
    double deputationAllowance;
    double bonus;
    double otherAllowance;
    double arear;
    double grossNetTotal;
    double pf;
    double pt;
    double tds;
    double otherDeduction;
    double grossDeduction;
    double netTakeHome;

    public SalaryResponseDTO(Salary s)
    {
        this.employee_id = s.getEmployee().getId();
        this.salaryDate = s.getSalaryDate();
        this.salaryMonth = s.getSalaryMonth();
        this.salaryYear = s.getSalaryYear();
        this.calenderDays = s.getCalenderDays();
        this.paidDays = s.getPaidDays();
        this.arearDays = s.getArearDays();
        this.overtimeDays = s.getOvertimeDays();
        this.overtimeRate = s.getOvertimeRate();
        this.lopDays = s.getLopDays();
        this.basic = s.getBasic();
        this.hra = s.getHra();
        this.conveyanceAllowance = s.getConveyanceAllowance();
        this.paymentsOvertime = s.getPaymentsOvertime();
        this.nightShiftAllowance = s.getNightShiftAllowance();
        this.specialAllowance = s.getSpecialAllowance();
        this.deputationAllowance = s.getDeputationAllowance();
        this.bonus = s.getBonus();
        this.otherAllowance = s.getOtherAllowance();
        this.arear = s.getArear();
        this.grossNetTotal = s.getGrossNetTotal();
        this.pf = s.getPf();
        this.pt = s.getPt();
        this.tds = s.getTds();
        this.otherDeduction = s.getOtherDeduction();
        this.grossDeduction = s.getGrossDeduction();
        this.netTakeHome = s.getNetTakeHome();
    }
}
