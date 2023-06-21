package ru.sber.models;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("Andrei")
public class CockatooParrot implements Parrot{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        setName("Andrei");
    }
    @Override
    public void say() {
        System.out.println("i'm cockatoo parrot");
    }
}
