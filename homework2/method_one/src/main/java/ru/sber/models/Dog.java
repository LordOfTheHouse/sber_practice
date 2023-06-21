package ru.sber.models;

public class Dog extends Animal{
    @Override
    public void say() {
        super.say();
        System.out.println("i'm dog");
    }
}
