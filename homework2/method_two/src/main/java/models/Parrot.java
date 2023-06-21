package models;


public class Parrot extends Animal{
    @Override
    public void say() {
        super.say();
        System.out.println("i'm parrot");
    }
}
