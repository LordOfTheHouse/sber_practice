package ru.sber.exceptions;

/**
 * Выбрасывается если коллекция пуста
 */
public class CollectionArgumentIsEmptyException extends RuntimeException {
    public CollectionArgumentIsEmptyException() {
    }

    public CollectionArgumentIsEmptyException(String message) {
        super(message);
    }
}
