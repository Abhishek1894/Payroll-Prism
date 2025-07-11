package com.markTech.payrollPrism.repository;

import com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeBasicInfoDTO;
import com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeDTO;
import com.markTech.payrollPrism.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Employee findByEmail(String email);


    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.active = false WHERE e.id = :id")
    int deactivateById(@Param("id") long id);


    @Query("SELECT new com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeBasicInfoDTO(e) from Employee e")
    List<EmployeeBasicInfoDTO> getEmployeeBasicInfo();

    @Query("SELECT new com.markTech.payrollPrism.DTO.EmployeeRelatedDTOS.EmployeeBasicInfoDTO(e) from Employee e WHERE e.id = :id")
    EmployeeBasicInfoDTO getEmployeeBasicInfoById(@Param("id") long id);

}
