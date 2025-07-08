package com.markTech.payrollPrism.service;
import com.markTech.payrollPrism.DTO.SalaryRelatedDTOS.SalaryDTO;
import com.markTech.payrollPrism.DTO.SalaryRelatedDTOS.SalaryResponseDTO;
import com.markTech.payrollPrism.customExceptions.ApplicationException;
import com.markTech.payrollPrism.customExceptions.InvalidFileException;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.model.Salary;
import com.markTech.payrollPrism.repository.EmployeeRepository;
import com.markTech.payrollPrism.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalarySerivce
{
    @Autowired
    ExcelService excelService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    public boolean uploadSalaryDetails(MultipartFile file) throws InvalidFileException, IOException, ApplicationException
    {
        System.out.println("Hello");
        if(file.isEmpty())
            throw new InvalidFileException("File Not Uploaded");

        if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                throw new InvalidFileException("Only .xlsx files are supported");

        List<SalaryDTO> s = excelService.getJsonFromExcelFile(file);

        System.out.println("converted");

        List<Salary> salaries = new ArrayList<>();

        for (SalaryDTO salaryDTO : s)
        {


            if (
                    salaryDTO.getEmployee_id().isEmpty() ||
                            salaryDTO.getSalaryDate().isEmpty() ||
                            salaryDTO.getSalaryMonth().isEmpty() ||
                            salaryDTO.getSalaryYear().isEmpty() ||
                            salaryDTO.getCalenderDays().isEmpty() ||
                            salaryDTO.getPaidDays().isEmpty() ||
                            salaryDTO.getArearDays().isEmpty() ||
                            salaryDTO.getOvertimeDays().isEmpty() ||
                            salaryDTO.getOvertimeRate().isEmpty() ||
                            salaryDTO.getLopDays().isEmpty() ||
                            salaryDTO.getBasic().isEmpty() ||
                            salaryDTO.getHra().isEmpty() ||
                            salaryDTO.getConveyanceAllowance().isEmpty() ||
                            salaryDTO.getPaymentsOvertime().isEmpty() ||
                            salaryDTO.getNightShiftAllowance().isEmpty() ||
                            salaryDTO.getSpecialAllowance().isEmpty() ||
                            salaryDTO.getDeputationAllowance().isEmpty() ||
                            salaryDTO.getBonus().isEmpty() ||
                            salaryDTO.getOtherAllowance().isEmpty() ||
                            salaryDTO.getArear().isEmpty() ||
                            salaryDTO.getGrossNetTotal().isEmpty() ||
                            salaryDTO.getPf().isEmpty() ||
                            salaryDTO.getPt().isEmpty() ||
                            salaryDTO.getTds().isEmpty() ||
                            salaryDTO.getOtherDeduction().isEmpty() ||
                            salaryDTO.getGrossDeduction().isEmpty() ||
                            salaryDTO.getNetTakeHome().isEmpty()
            )
            {
                throw new ApplicationException("One or more required fields are empty");
            }


            long id = Double.valueOf(salaryDTO.getEmployee_id()).longValue();
            Employee e = employeeRepository.findById(id).orElse(null);
            if (e == null)
                throw new ApplicationException("Employee with id: " + salaryDTO.getEmployee_id() + " not found");


            Salary salary = new Salary();

            System.out.println(e);

            String salaryId = id + "-" + salaryDTO.getSalaryMonth().toUpperCase() + "-" + Double.valueOf(salaryDTO.getSalaryYear()).intValue();
            salary.setId(salaryId);
            salary.setEmployee(e);
            salary.setSalaryDate(salaryDTO.getSalaryDate());
            salary.setSalaryMonth(salaryDTO.getSalaryMonth().toUpperCase());
            salary.setSalaryYear(Double.valueOf(salaryDTO.getSalaryYear()).intValue());
            salary.setCalenderDays(Double.parseDouble(salaryDTO.getCalenderDays()));
            salary.setPaidDays(Double.parseDouble(salaryDTO.getPaidDays()));
            salary.setArearDays(Double.parseDouble(salaryDTO.getArearDays()));
            salary.setOvertimeDays(Double.parseDouble(salaryDTO.getOvertimeDays()));
            salary.setOvertimeRate(Double.parseDouble(salaryDTO.getOvertimeRate()));
            salary.setLopDays(Double.parseDouble(salaryDTO.getLopDays()));
            salary.setBasic(Double.parseDouble(salaryDTO.getBasic()));
            salary.setHra(Double.parseDouble(salaryDTO.getHra()));
            salary.setConveyanceAllowance(Double.parseDouble(salaryDTO.getConveyanceAllowance()));
            salary.setPaymentsOvertime(Double.parseDouble(salaryDTO.getPaymentsOvertime()));
            salary.setNightShiftAllowance(Double.parseDouble(salaryDTO.getNightShiftAllowance()));
            salary.setSpecialAllowance(Double.parseDouble(salaryDTO.getSpecialAllowance()));
            salary.setDeputationAllowance(Double.parseDouble(salaryDTO.getDeputationAllowance()));
            salary.setBonus(Double.parseDouble(salaryDTO.getBonus()));
            salary.setOtherAllowance(Double.parseDouble(salaryDTO.getOtherAllowance()));
            salary.setArear(Double.parseDouble(salaryDTO.getArear()));
            salary.setGrossNetTotal(Double.parseDouble(salaryDTO.getGrossNetTotal()));
            salary.setPf(Double.parseDouble(salaryDTO.getPf()));
            salary.setPt(Double.parseDouble(salaryDTO.getPt()));
            salary.setTds(Double.parseDouble(salaryDTO.getTds()));
            salary.setOtherDeduction(Double.parseDouble(salaryDTO.getOtherDeduction()));
            salary.setGrossDeduction(Double.parseDouble(salaryDTO.getGrossDeduction()));
            salary.setNetTakeHome(Double.parseDouble(salaryDTO.getNetTakeHome()));

            salaries.add(salary);

        }

        // saving all the salaries in database
        for (Salary salary : salaries)
            salaryRepository.save(salary);


        return true;

    }


    public List<SalaryResponseDTO> getSalary(long employeeId)
    {
        return salaryRepository.findSalaryById(employeeId);
    }
}
