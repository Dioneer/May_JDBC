package org.example.exception;

public class DaoException extends RuntimeException {
    public DaoException(String message, Throwable e) {
        super(message, e);
    }
}
