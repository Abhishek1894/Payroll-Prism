package com.markTech.payrollPrism.controller;

import com.markTech.payrollPrism.customExceptions.InvalidCredentialsException;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController
{

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String greet()
    {
        return "Welcome to Payroll Prism";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee)
    {
        Employee e = employeeService.registerEmployee(employee);

        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @PostMapping("/auth/signIn")
    public ResponseEntity<?> signIn(@RequestBody Employee employee)
    {
        try
        {
            String token = employeeService.verify(employee);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Invalid Email or Password", HttpStatus.UNAUTHORIZED);
        }
    }
}
