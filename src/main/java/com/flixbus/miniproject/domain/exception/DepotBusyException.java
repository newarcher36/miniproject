package com.flixbus.miniproject.domain.exception;

public class DepotBusyException extends RuntimeException {
    public DepotBusyException(String message) {
        super(message);
    }
}
