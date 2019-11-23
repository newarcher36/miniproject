package com.flixbus.miniproject.domain.exception;

public class BusAlreadyParkedExcepion extends RuntimeException {

    public BusAlreadyParkedExcepion(String message) {
        super(message);
    }
}
