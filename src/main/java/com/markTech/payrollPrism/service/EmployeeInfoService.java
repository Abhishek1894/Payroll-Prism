package com.markTech.payrollPrism.service;

import com.markTech.payrollPrism.model.EmployeeInfo;
import com.markTech.payrollPrism.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeInfoService
{
    @Autowired
    EmployeeInfoRepository employeeInfoRepository;

    public EmployeeInfo registerEmployeeInfo(EmployeeInfo employeeInfo)
    {
        return employeeInfoRepository.save(employeeInfo);
    }

    public List<EmployeeInfo> getEmployeeInfos()
    {
        return employeeInfoRepository.findAll();
    }

    public EmployeeInfo getEmployeeInfo(long id)
    {
        return employeeInfoRepository.findById(id).orElse(null);
    }
}
