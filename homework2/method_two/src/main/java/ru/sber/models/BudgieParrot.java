package ru.sber.models;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("Koko")
public class BudgieParrot implements Parrot{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        setName("Koko");
    }
    @Override
    public void say() {
        System.out.println("i'm cockatoo parrot");
    }
}
