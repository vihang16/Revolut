package com.revolut.domain.exception;

public class LimitExceedException extends RuntimeException{
    public LimitExceedException(String message) {
        super(message);
    }
}
