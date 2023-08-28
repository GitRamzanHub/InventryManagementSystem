package com.inventrymanagementsystem.ramzan.exception;

public class PortNotFound extends RuntimeException{

    private String message;

    public PortNotFound(String message){
        super(message);
        this.message = message;
    }
}
