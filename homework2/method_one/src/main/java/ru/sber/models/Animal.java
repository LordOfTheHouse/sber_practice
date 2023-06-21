package ru.sber.models;

public abstract class Animal {
    private String name;

    public void say() {
        System.out.print("My name " + name + ", ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
