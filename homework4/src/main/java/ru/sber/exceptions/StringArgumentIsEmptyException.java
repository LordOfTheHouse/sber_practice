package ru.sber.exceptions;

/**
 * Выбрасывается если строка пуста
 */
public class StringArgumentIsEmptyException extends RuntimeException {

    public StringArgumentIsEmptyException() {
    }

    public StringArgumentIsEmptyException(String message) {
        super(message);
    }
}
