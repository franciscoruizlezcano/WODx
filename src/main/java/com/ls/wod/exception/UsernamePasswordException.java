package com.ls.wod.exception;

/**
 *
 * @author francisco
 */
public class UsernamePasswordException extends RuntimeException{

    public UsernamePasswordException() {
        super("Wrong username or password");
    }

}
