package com.flixbus.miniproject.domain.exception;

public class BusNotFoundException extends  RuntimeException {

    public BusNotFoundException(String message) {
        super(message);
    }
}
