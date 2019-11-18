package com.flixbus.miniproject.domain.exception;

public class DuplicatePlateNumberException extends RuntimeException {
    public DuplicatePlateNumberException(String message) {
        super(message);
    }
}
