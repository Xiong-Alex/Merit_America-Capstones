package com.techelevator.tenmo.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
