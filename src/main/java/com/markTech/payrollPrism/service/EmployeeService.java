package com.markTech.payrollPrism.service;

import com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeBasicInfoDTO;
import com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeCredDTO;
import com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeDTO;
import com.markTech.payrollPrism.customExceptions.ApplicationException;
import com.markTech.payrollPrism.customExceptions.InvalidCredentialsException;
import com.markTech.payrollPrism.customExceptions.InvalidFileException;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.repository.EmployeeRepository;
import org.hibernate.id.IncrementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<EmployeeDTO> getEmployees(int offset, int limit)
    {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> response = new ArrayList<>();

        int count = 0;
        int start = offset;
        while(start < employees.size() && count < limit)
        {
            response.add(new EmployeeDTO(employees.get(start)));
            start++;
            count++;
        }

        return response;
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

    public EmployeeDTO updateEmployee(Employee employee) throws IOException,InvalidFileException
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


    public EmployeeDTO updateEmployeeProfileImage(long id, MultipartFile image) throws InvalidFileException, ApplicationException, IOException
    {
        if(image.isEmpty())
            throw new InvalidFileException("Image not uploaded");

        String contentType = image.getContentType();
        if(!(contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/jpg") || contentType.equalsIgnoreCase("image/png")))
            throw new InvalidFileException("Only jpeg, png and jpg allowed");

        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null)
            throw new ApplicationException("Employee with id : " + id + "do not exist");

        employee.setProfileImage(image.getBytes());
        employee = employeeRepository.save(employee);
        return new EmployeeDTO(employee);
    }

    // service for deleting the employees (never used due to join dependency)
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
