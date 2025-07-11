package com.markTech.payrollPrism.controller;

import com.markTech.payrollPrism.DTO.RequestDTOS.IdMonthYearDTO;
import com.markTech.payrollPrism.DTO.SalaryRelatedDTOS.SalaryResponseDTO;
import com.markTech.payrollPrism.customExceptions.ApplicationException;
import com.markTech.payrollPrism.customExceptions.InvalidFileException;
import com.markTech.payrollPrism.model.Salary;
import com.markTech.payrollPrism.repository.SalaryRepository;
import com.markTech.payrollPrism.service.SalarySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SalaryController
{
    @Autowired
    SalarySerivce salarySerivce;

    @PostMapping("/salary")
    public ResponseEntity<?> uploadSalaryDetails(@RequestParam("file") MultipartFile file) throws InvalidFileException, IOException, ApplicationException
    {
        return new ResponseEntity<>(salarySerivce.uploadSalaryDetails(file), HttpStatus.CREATED);
    }

    @GetMapping("/salary/{employeeId}")
    public ResponseEntity<List<SalaryResponseDTO>> getSalary(@PathVariable long employeeId, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit)
    {
        return new ResponseEntity<>(salarySerivce.getSalary(employeeId,offset,limit), HttpStatus.OK);
    }

    @GetMapping("/salary")
    public ResponseEntity<?> getSalaryByIdMonthYear(@RequestBody IdMonthYearDTO body) throws ApplicationException
    {
        System.out.println("Entered");
        long id = body.getId();
        int month = body.getMonth();
        int year = body.getYear();


        return new ResponseEntity<>(salarySerivce.getSalaryByIdMonthYear(id, month, year), HttpStatus.OK);
    }


}
