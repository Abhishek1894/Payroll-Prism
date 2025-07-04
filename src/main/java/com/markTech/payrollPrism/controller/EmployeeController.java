package com.markTech.payrollPrism.controller;

import com.markTech.payrollPrism.DTO.EmployeeDetailDTO;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDetailDTO>> getEmployees()
    {
        List<Employee> employees = employeeService.getEmployees();
        List<EmployeeDetailDTO> array = new ArrayList<>();

        for(Employee employee : employees)
            array.add(new EmployeeDetailDTO(employee));

        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDetailDTO> getEmployee(@PathVariable long id)
    {
        Employee employee = employeeService.getEmployee(id);

        if(employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new EmployeeDetailDTO(employee), HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<EmployeeDetailDTO> updateEmployee(@RequestBody Employee employee)
    {
        Employee emp= employeeService.updateEmployee(employee);
        return emp == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(new EmployeeDetailDTO(emp), HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeDetailDTO> deleteEmployee(@PathVariable long id)
    {
        Employee emp = employeeService.deleteEmployee(id);
        return emp == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(new EmployeeDetailDTO(emp), HttpStatus.OK);
    }


    @PatchMapping("/employee/deactivate/{id}")
    public ResponseEntity<EmployeeDetailDTO> deactivateEmployee(@PathVariable long id)
    {
        Employee emp = employeeService.deactivateEmployee(id);
        return emp == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(new EmployeeDetailDTO(emp), HttpStatus.OK);
    }
}
