package com.markTech.payrollPrism.exceptionHandler;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.markTech.payrollPrism.customExceptions.ApplicationException;
import com.markTech.payrollPrism.customExceptions.InvalidFileException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    final String duplicateEmailConstraint = "ukfopic1oh5oln2khj8eat6ino0";
    final String primaryKeyConstraint = "employee_pkey";

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(DataIntegrityViolationException ex) {
        Throwable cause = ex.getCause();
        String message = "Data integrity error";

        if (cause instanceof ConstraintViolationException cve) {
            String constraint = cve.getConstraintName();
            if (duplicateEmailConstraint.equals(constraint)) {
                message = "Duplicate email address";
            } else if (primaryKeyConstraint.equals(constraint)) {
                message = "Duplicate Id";
            } else {
                message = "Constraint violated: " + constraint;
            }
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", message));
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<?> handleInvalidFile(InvalidFileException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }
}
