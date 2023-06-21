package ru.sber.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name;
    private Cat cat;
    private Dog dog;
    private Parrot parrotOne;
    private Parrot parrotTwo;

    public Person(@Qualifier("Andrei")Parrot parrotOne, @Qualifier("Koko")Parrot parrotTwo) {
        this.parrotOne = parrotOne;
        this.parrotTwo = parrotTwo;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Cat getCat() {
        return cat;
    }
    @Autowired
    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }
    @Autowired
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Parrot getParrotOne() {
        return parrotOne;
    }

    public void setParrotOne(Parrot parrotOne) {
        this.parrotOne = parrotOne;
    }

    public Parrot getParrotTwo() {
        return parrotTwo;
    }
    public void setParrotTwo(Parrot parrotTwo) {
        this.parrotTwo = parrotTwo;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cat=" + cat.getName() +
                ", dog=" + dog.getName() +
                ", parrot_one=" + parrotOne.getName() +
                ", parrot_two=" + parrotTwo.getName() +
                '}';
    }
}
