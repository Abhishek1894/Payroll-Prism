package com.markTech.payrollPrism.customExceptions;

import java.util.List;

public class InvalidFileException extends Exception
{
    String message;
    List<String> messages;

    public InvalidFileException()
    {
        this.message = "Invalid File";
    }

    public InvalidFileException(String message)
    {
        this.message = message;
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

    public List<String> getDetailMessage()
    {
        return this.messages;
    }
}
