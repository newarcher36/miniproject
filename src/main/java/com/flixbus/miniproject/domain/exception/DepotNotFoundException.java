package com.flixbus.miniproject.domain.exception;

public class DepotNotFoundException extends RuntimeException {
    public DepotNotFoundException(String message) {
        super(message);
    }
}
