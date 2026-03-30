package org.example.homework003.exception;

public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException() {
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}
