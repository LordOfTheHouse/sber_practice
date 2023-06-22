package ru.sber.exceptions;

public class CollectionArgumentIsEmptyException extends RuntimeException {
    public CollectionArgumentIsEmptyException() {
    }

    public CollectionArgumentIsEmptyException(String message) {
        super(message);
    }
}
