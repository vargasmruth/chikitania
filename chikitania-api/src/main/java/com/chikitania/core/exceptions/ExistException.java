package com.chikitania.core.exceptions;

/**
 * Created by veronica on 03/11/2017.
 */
public class ExistException extends Exception {
    public ExistException() {
        super("the resource already exists in the database");
    }

    public ExistException(String message) {
        super(message);
    }
}
