package ru.sber.models;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Dog extends Animal{
    @PostConstruct
    public void init() {
        setName("Sharik");
    }
    @Override
    public void say() {
        super.say();
        System.out.println("i'm dog");
    }
}
