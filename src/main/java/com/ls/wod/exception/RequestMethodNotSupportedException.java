package com.ls.wod.exception;

/**
 *
 * @author francisco
 */

public class RequestMethodNotSupportedException extends RuntimeException{

    public RequestMethodNotSupportedException() {
        super("Request method not supported");
    }

}
