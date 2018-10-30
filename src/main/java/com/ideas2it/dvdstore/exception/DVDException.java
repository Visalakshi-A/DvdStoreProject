package com.ideas2it.dvdstore.exception;

/**
 * Extends the {@code Exception} class to create a custom exception
 * for the DVD Store.
 *
 * @version 1
 * @author Visalakshi
 */
public class DVDException extends Exception {

    public DVDException(String e) {
        super(e);
    }
    public DVDException(String e, Throwable cause) {
        super(e, cause);
    }
    public DVDException(Throwable cause) {
        super(cause);
    }
}
