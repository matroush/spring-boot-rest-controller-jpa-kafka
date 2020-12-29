package com.peopleflw.ems.exceptions;

public class InvalidEmployeeStateException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public InvalidEmployeeStateException(String msg){

        super(msg);
    }
}
