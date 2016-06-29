package com.bms.system.bean;

import java.io.PrintStream;
import java.io.PrintWriter;

public class PPException extends RuntimeException
{

    private static final long serialVersionUID = -6468352029138033138L;

    public PPException()
    {
        super();
    }

    public PPException(String message)
    {
        super(message);
    }

    public PPException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PPException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace()
    {
        return super.fillInStackTrace();
    }

    @Override
    public Throwable getCause()
    {
        return super.getCause();
    }

    @Override
    public String getLocalizedMessage()
    {
        return super.getLocalizedMessage();
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }

    @Override
    public StackTraceElement[] getStackTrace()
    {
        return super.getStackTrace();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause)
    {
        return super.initCause(cause);
    }

    @Override
    public void printStackTrace()
    {
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s)
    {
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s)
    {
        super.printStackTrace(s);
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace)
    {
        super.setStackTrace(stackTrace);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

}
