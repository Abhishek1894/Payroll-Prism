package com.markTech.payrollPrism.customExceptions;

public class ApplicationException extends Exception
{
    String message;

    public ApplicationException(String messsage)
    {
        this.message = messsage;
    }

    @Override
    public String toString()
    {
        return this.message;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
