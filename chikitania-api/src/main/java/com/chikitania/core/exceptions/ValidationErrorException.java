package com.chikitania.core.exceptions;

/**
 * Created by Ruth on 23/09/2019.
 */
 
public class ValidationErrorException extends Exception {
    public ValidationErrorException(String message) {
        super(message);
    }
}
