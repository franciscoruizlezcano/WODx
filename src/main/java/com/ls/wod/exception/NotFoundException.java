package com.ls.wod.exception;

/**
 *
 * @author francisco
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("Entity not found");
    }

}
