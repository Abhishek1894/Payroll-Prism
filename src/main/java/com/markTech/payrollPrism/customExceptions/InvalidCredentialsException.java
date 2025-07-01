package com.markTech.payrollPrism.customExceptions;

public class InvalidCredentialsException extends Exception
{
    @Override
    public String toString()
    {
        return "Invalid Email or Password";
    }

    @Override
    public String getMessage()
    {
        return "Invalid Email or Password";
    }

}
