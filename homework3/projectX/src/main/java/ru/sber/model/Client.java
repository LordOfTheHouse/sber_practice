package ru.sber.model;

public class Client {

    private String name;
    private long userId;

    public Client(String name, long userId) {
        this.name = name;
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return userId == client.userId;
    }

    @Override
    public int hashCode() {
        return (int) userId;
    }
}
