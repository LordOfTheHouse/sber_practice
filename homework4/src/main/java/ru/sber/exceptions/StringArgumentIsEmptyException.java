package ru.sber.exceptions;

public class StringArgumentIsEmptyException extends RuntimeException {

    public StringArgumentIsEmptyException() {
    }

    public StringArgumentIsEmptyException(String message) {
        super(message);
    }
}
