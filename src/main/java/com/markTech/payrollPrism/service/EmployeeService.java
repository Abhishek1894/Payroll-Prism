package com.markTech.payrollPrism.service;

import com.markTech.payrollPrism.DTO.EmployeeBasicInfoDTO;
import com.markTech.payrollPrism.DTO.EmployeeCredDTO;
import com.markTech.payrollPrism.DTO.EmployeeDTO;
import com.markTech.payrollPrism.customExceptions.InvalidCredentialsException;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // password encoder; IMP
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // service to register the employees for first time
    public EmployeeCredDTO registerEmployee(Employee employee)
    {
        // employee.setEmployee(null);
        employee.setPassword(encoder.encode(employee.getPassword()));
        Employee defaultManager = new Employee();
        defaultManager.setId(1017L);
        employee.setManager(defaultManager);
        Employee emp = employeeRepository.save(employee);
        return new EmployeeCredDTO(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmail());
    }

    // service to get list of all the employees
    public List<EmployeeDTO> getEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> array = new ArrayList<>();

        for(Employee employee : employees)
            array.add(new EmployeeDTO(employee));

        return array;
    }

    // service for getting the basic information of the employee
    public List<EmployeeBasicInfoDTO> getEmployeeBasicInfo()
    {
        return employeeRepository.getEmployeeBasicInfo();
    }

    // used for authenticating the user and giving the token in return; IMP
    public String verify(Employee employee) throws InvalidCredentialsException
    {
        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(employee.getEmail());

        System.out.println("Throwing exception");
        throw new InvalidCredentialsException();
    }


    // service for getting the employees based on id
    public EmployeeDTO getEmployee(long id)
    {
        Employee employee = employeeRepository.findById(id).orElse(null);

        return employee == null ? null : new EmployeeDTO(employee);
    }

    public EmployeeDTO updateEmployee(Employee employee)
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

        Employee savedEmployee = employeeRepository.save(emp);

        return new EmployeeDTO(savedEmployee);
    }


    // service for deleting the employees
    public EmployeeDTO deleteEmployee(long id)
    {
        Employee emp = employeeRepository.findById(id).orElse(null);

        if(emp == null)
            return null;

        employeeRepository.deleteById(id);
        return new EmployeeDTO(emp);
    }

    // service for deactivating the employees
    public EmployeeDTO deactivateEmployee(long id)
    {
        Employee emp = employeeRepository.findById(id).orElse(null);

        if(emp == null)
            return null;

        int val = employeeRepository.deactivateById(id);
        return new EmployeeDTO(emp);
    }

    public EmployeeBasicInfoDTO getEmployeeBasicInfoById(long id)
    {
        return employeeRepository.getEmployeeBasicInfoById(id);
    }
}
