package com.markTech.payrollPrism.repository;

import com.markTech.payrollPrism.DTO.SalaryRelatedDTOS.SalaryResponseDTO;
import com.markTech.payrollPrism.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long>
{
    @Query("SELECT new com.markTech.payrollPrism.DTO.SalaryRelatedDTOS.SalaryResponseDTO(s) from Salary s WHERE s.employee.id = :id")
    public List<SalaryResponseDTO> findSalaryById(@Param("id") long id);

    @Query("SELECT new com.markTech.payrollPrism.DTO.SalaryRelatedDTOS.SalaryResponseDTO(s) from Salary s WHERE s.employee.id = :id AND s.salaryMonth = :month AND s.salaryYear = :year")
    public List<SalaryResponseDTO> fetchSalaryByIdMonthAndYear(@Param("id") long id,@Param("month") String month, @Param("year") int year);
}
