package com.markTech.payrollPrism.controller;

import com.markTech.payrollPrism.DTO.EmployeeDetailDTO;
import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.model.EmployeeInfo;
import com.markTech.payrollPrism.service.EmployeeInfoService;
import com.markTech.payrollPrism.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDetailDTO>> getEmployees()
    {
        // we should use join here but ok for now
        List<Employee> employees = employeeService.getEmployees();
        List<EmployeeInfo> employeeInfos = employeeInfoService.getEmployeeInfos();

        HashMap<Long, EmployeeInfo> map = new HashMap<>();

        for(EmployeeInfo info: employeeInfos)
            map.put(info.getId(), info);

        List<EmployeeDetailDTO> array = new ArrayList<>();

        for(Employee e : employees)
        {
            EmployeeInfo info = map.get(e.getId());

            if(info != null)
                array.add(new EmployeeDetailDTO(e, info));
        }

        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDetailDTO> getEmployee(@PathVariable long id)
    {
        Employee employee = employeeService.getEmployee(id);

        if(employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfo(id);

        return new ResponseEntity<>(new EmployeeDetailDTO(employee, employeeInfo), HttpStatus.OK);
    }
}
