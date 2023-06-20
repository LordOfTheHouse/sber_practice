package models;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Cat extends Animal{

    @PostConstruct
    public void init() {
        setName("Vasya");
    }
    @Override
    public void say() {
        super.say();
        System.out.println("i'm cat");
    }
}
