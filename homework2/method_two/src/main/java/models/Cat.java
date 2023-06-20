package models;

public class Cat extends Animal{
    @Override
    public void say() {
        super.say();
        System.out.println("i'm cat");
    }
}
