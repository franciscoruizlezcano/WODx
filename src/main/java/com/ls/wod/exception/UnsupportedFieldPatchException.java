package com.ls.wod.exception;

import java.util.Set;

/**
 *
 * @author francisco
 */
class UnsupportedFieldPatchException extends RuntimeException{

    public UnsupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
