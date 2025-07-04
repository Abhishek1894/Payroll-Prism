package com.markTech.payrollPrism.service;

import com.markTech.payrollPrism.customExceptions.InvalidCredentialsException;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Employee registerEmployee(Employee employee)
    {
        // employee.setEmployee(null);
        employee.setPassword(encoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees()
    {
        return employeeRepository.findAll();
    }


    public String verify(Employee employee) throws InvalidCredentialsException
    {
        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(employee.getEmail());

        System.out.println("Throwing exception");
        throw new InvalidCredentialsException();
    }

    public Employee getEmployee(long id)
    {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Employee employee)
    {
        Employee emp = employeeRepository.findById(employee.getId()).orElse(null);

        if(emp == null)
            return null;

        if(employee.getFirstName() != null)
            emp.setFirstName(employee.getFirstName());

        if(employee.getLastName() != null)
            emp.setLastName(employee.getLastName());

        if(employee.getEmail() != null)
            emp.setEmail(employee.getEmail());

        if(employee.isActive() != emp.isActive())
            emp.setActive(employee.isActive());

        if(employee.getRole() != null)
            emp.setRole(employee.getRole());

        if(employee.getContactNo() != null)
            emp.setContactNo(employee.getContactNo());

        if(employee.getGender() != null)
            emp.setGender(employee.getGender());

        if(employee.getBankAccNo() != null)
            emp.setBankAccNo(employee.getBankAccNo());

        if(employee.getBusinessUnit() != null)
            emp.setBusinessUnit(employee.getBusinessUnit());

        if(employee.getBankName() != null)
            emp.setBankName(employee.getBankName());

        // so on

        employeeRepository.save(emp);
        return employee;
    }


    public Employee deleteEmployee(long id)
    {
        Employee emp = employeeRepository.findById(id).orElse(null);

        if(emp == null)
            return null;

        employeeRepository.deleteById(id);
        return emp;
    }

    public Employee deactivateEmployee(long id)
    {
        Employee emp = employeeRepository.findById(id).orElse(null);

        if(emp == null)
            return null;

        int val = employeeRepository.deactivateById(id);
        return emp;
    }
}
