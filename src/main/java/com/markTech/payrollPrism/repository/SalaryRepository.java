package com.markTech.payrollPrism.repository;

import com.markTech.payrollPrism.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long>
{

}
