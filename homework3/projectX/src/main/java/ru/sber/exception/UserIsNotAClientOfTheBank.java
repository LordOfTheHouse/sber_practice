package ru.sber.exception;

public class UserIsNotAClientOfTheBank extends Exception {
    public UserIsNotAClientOfTheBank() {
    }

    public UserIsNotAClientOfTheBank(String message) {
        super(message);
    }
}
