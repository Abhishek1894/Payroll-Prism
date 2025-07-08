package com.markTech.payrollPrism.controller;

import com.markTech.payrollPrism.customExceptions.ApplicationException;
import com.markTech.payrollPrism.customExceptions.InvalidFileException;
import com.markTech.payrollPrism.service.SalarySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class SalaryController
{
    @Autowired
    SalarySerivce salarySerivce;

    @PostMapping("/salary")
    public ResponseEntity<?> uploadSalaryDetails(@RequestParam("file") MultipartFile file) throws InvalidFileException, IOException, ApplicationException {
        return new ResponseEntity<>(salarySerivce.uploadSalaryDetails(file), HttpStatus.CREATED);
    }
}
