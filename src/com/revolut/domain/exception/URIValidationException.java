package com.revolut.domain.exception;

public class URIValidationException extends  RuntimeException{

    public URIValidationException(String message) {
        super(message);
    }
}
