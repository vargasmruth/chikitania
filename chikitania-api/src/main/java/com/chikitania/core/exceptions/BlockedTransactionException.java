package com.chikitania.core.exceptions;

/**
 * Created by Ruth on 23/09/2019.
 */
 
public class BlockedTransactionException extends Exception{
    public BlockedTransactionException(String message) {
        super(message);
    }
}
