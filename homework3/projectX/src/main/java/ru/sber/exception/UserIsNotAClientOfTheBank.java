package ru.sber.exception;

/**
 * Исключение возникающее если пользователь не является клиентом банка
 */
public class UserIsNotAClientOfTheBank extends Exception {
    public UserIsNotAClientOfTheBank() {
    }

    public UserIsNotAClientOfTheBank(String message) {
        super(message);
    }
}
