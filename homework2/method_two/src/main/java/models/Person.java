package models;

public class Person {
    public Person(Cat cat, Dog dog, Parrot parrot_one, Parrot parrot_two) {
        this.cat = cat;
        this.dog = dog;
        this.parrot_one = parrot_one;
        this.parrot_two = parrot_two;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cat=" + cat.getName() +
                ", dog=" + dog.getName() +
                ", parrot_one=" + parrot_one.getName() +
                ", parrot_two=" + parrot_two.getName() +
                '}';
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
        return parrot_one;
    }

    public void setParrot_one(Parrot parrot_one) {
        this.parrot_one = parrot_one;
    }

    public Parrot getParrot_two() {
        return parrot_two;
    }

    public void setParrot_two(Parrot parrot_two) {
        this.parrot_two = parrot_two;
    }

    private String name;
    private Cat cat;
    private Dog dog;
    private Parrot parrot_one;
    private Parrot parrot_two;
}
