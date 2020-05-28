package com.ls.wod.exception;

/**
 *
 * @author francisco
 */
public class AuthorizationException extends RuntimeException{

    public AuthorizationException() {
        super("No authorization");
    }
    
}
