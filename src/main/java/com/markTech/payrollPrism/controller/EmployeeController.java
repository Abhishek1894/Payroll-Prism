package com.markTech.payrollPrism.controller;

import com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeBasicInfoDTO;
import com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeDTO;
import com.markTech.payrollPrism.customExceptions.ApplicationException;
import com.markTech.payrollPrism.customExceptions.InvalidFileException;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit)
    {
        return new ResponseEntity<>(employeeService.getEmployees(offset, limit), HttpStatus.OK);
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable long id)
    {
        EmployeeDTO employee = employeeService.getEmployee(id);

        if(employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @GetMapping("/employee/basicInfo")
    public ResponseEntity<List<EmployeeBasicInfoDTO>> getEmployeeBasicInfo()
    {
        List<EmployeeBasicInfoDTO> list = employeeService.getEmployeeBasicInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/employee/basicInfo/{id}")
    public ResponseEntity<EmployeeBasicInfoDTO> getEmployeeBasicInfoById(@PathVariable long id)
    {
        EmployeeBasicInfoDTO e = employeeService.getEmployeeBasicInfoById(id);
        return e == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(e, HttpStatus.OK);
    }


    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws InvalidFileException, IOException
    {

        EmployeeDTO emp= employeeService.updateEmployee(employee);
        return emp == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(emp, HttpStatus.OK);
    }


    @PutMapping("/profileImage/{id}")
    public ResponseEntity<?> updateProfileImage(@PathVariable long id, @RequestParam("image") MultipartFile image) throws ApplicationException, InvalidFileException, IOException
    {
        EmployeeDTO employeeDTO = employeeService.updateEmployeeProfileImage(id, image);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable long id)
    {
        EmployeeDTO emp = employeeService.deleteEmployee(id);
        return emp == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(emp, HttpStatus.OK);
    }


    @PatchMapping("/employee/deactivate/{id}")
    public ResponseEntity<EmployeeDTO> deactivateEmployee(@PathVariable long id)
    {
        EmployeeDTO emp = employeeService.deactivateEmployee(id);
        return emp == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(emp, HttpStatus.OK);
    }

}
