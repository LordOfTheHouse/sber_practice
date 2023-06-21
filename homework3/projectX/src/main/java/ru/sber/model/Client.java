package ru.sber.model;

/**
 * Клиент
 */
public class Client {

    private String name;
    private long userId;

    private String phoneNumber;

    public Client(String name, long userId, String phoneNumber) {
        this.name = name;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
