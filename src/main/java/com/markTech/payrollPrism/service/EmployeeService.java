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
}
