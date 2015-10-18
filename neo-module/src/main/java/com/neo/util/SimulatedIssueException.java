package com.neo.util;

/**
 * A runtime exception which aims to indicate that the exception
 * was specifically created to simulate an error situation
 */
public class SimulatedIssueException extends RuntimeException {

    public SimulatedIssueException(String message) {
        super(message);
    }

}
