package org.example.exception;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
    public DaoException(String message, Throwable e) {
        super(message, e);
    }
}
