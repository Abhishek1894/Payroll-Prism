package com.markTech.payrollPrism.service;

import com.markTech.payrollPrism.model.Employee;
import com.markTech.payrollPrism.model.EmployeePrincipal;
import com.markTech.payrollPrism.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class EmployeeDetailService implements UserDetailsService
{
    @Autowired
    EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Employee employee = repository.findByEmail(email);

        if(employee == null)
            throw new UsernameNotFoundException("email not found");

        return new EmployeePrincipal(employee);
    }
}
