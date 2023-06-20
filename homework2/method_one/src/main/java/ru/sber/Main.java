package ru.sber;

import conf.ConfigApp;
import models.Cat;
import models.Dog;
import models.Parrot;
import models.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ConfigApp.class);

        Person p = context.getBean(Person.class);
        Parrot parrot1 = context.getBean("parrot_one", Parrot.class);
        Parrot parrot2 = context.getBean("parrot_two", Parrot.class);
        Cat cat = context.getBean(Cat.class);
        Dog dog = context.getBean(Dog.class);
        System.out.println(p);
        parrot1.say();
        parrot2.say();
        cat.say();
        dog.say();

    }
}