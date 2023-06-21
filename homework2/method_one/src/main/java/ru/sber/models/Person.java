package ru.sber.models;

public class Person {

    private String name;
    private Cat cat;
    private Dog dog;
    private Parrot parrotOne;
    private Parrot parrotTwo;

    public Person(Cat cat, Dog dog, Parrot parrot_one, Parrot parrot_two) {
        this.cat = cat;
        this.dog = dog;
        this.parrotOne = parrot_one;
        this.parrotTwo = parrot_two;
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

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Parrot getParrot_one() {
        return parrotOne;
    }

    public void setParrot_one(Parrot parrot_one) {
        this.parrotOne = parrotOne;
    }

    public Parrot getParrot_two() {
        return parrotTwo;
    }

    public void setParrot_two(Parrot parrotTwo) {
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
