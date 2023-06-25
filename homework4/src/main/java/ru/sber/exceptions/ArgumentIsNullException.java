package ru.sber.exceptions;

/**
 * Выбрасывается если аргумент == null
 */
public class ArgumentIsNullException extends RuntimeException{
    public ArgumentIsNullException() {
    }

    public ArgumentIsNullException(String message) {
        super(message);
    }
}
