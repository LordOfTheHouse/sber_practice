package ru.sber.exceptions;

public class ArgumentIsNullException extends RuntimeException{
    public ArgumentIsNullException() {
    }

    public ArgumentIsNullException(String message) {
        super(message);
    }
}
