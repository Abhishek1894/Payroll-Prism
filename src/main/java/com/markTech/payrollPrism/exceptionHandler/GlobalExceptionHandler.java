package com.markTech.payrollPrism.exceptionHandler;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{

    final String duplicateEmailConstraint =  "ukfopic1oh5oln2khj8eat6ino0";
    final String primaryKeyConstraint = "employee_pkey";

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(DataIntegrityViolationException ex)
    {
        Throwable cause = ex.getCause();
        String message = "Data integrity error";

        if (cause instanceof ConstraintViolationException cve)
        {
            String constraint = cve.getConstraintName();  // e.g., "user_email_key" or "email_UNIQUE"
            if (duplicateEmailConstraint.equals(constraint))
            {
                message = "Duplicate email address";
            }
            else if(primaryKeyConstraint.equals(constraint))
            {
                message = "Duplicate Id";
            }
            else
            {
                message = "Constraint violated: " + constraint;
            }
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(message);
    }
}
