package com.flixbus.miniproject.domain.exception;

public class DepotNotEmptyException extends RuntimeException {
    public DepotNotEmptyException(String message) {
        super(message);
    }
}
