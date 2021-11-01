package com.fernandonieto.hackdetection.domain.exception;

public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = -2386452178326638319L;

    public InvalidDataException() {

    }

    public InvalidDataException(final String message) {
        super(message);
    }

    public InvalidDataException(final String message, final Throwable cause) {
        super(message, cause);
    }
}