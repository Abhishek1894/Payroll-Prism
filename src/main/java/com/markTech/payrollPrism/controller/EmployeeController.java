package com.markTech.payrollPrism.controller;

import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployees()
    {
        List<Employee> list = employeeService.getEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
