package ru.sber;

import ru.sber.conf.ConfigApp;
import ru.sber.models.Cat;
import ru.sber.models.Dog;
import ru.sber.models.Parrot;
import ru.sber.models.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ConfigApp.class);

        Person p = context.getBean(Person.class);
        Parrot parrot1 = context.getBean("parrotOne", Parrot.class);
        Parrot parrot2 = context.getBean("parrotTwo", Parrot.class);
        Cat cat = context.getBean(Cat.class);
        Dog dog = context.getBean(Dog.class);
        System.out.println(p);
        parrot1.say();
        parrot2.say();
        cat.say();
        dog.say();

    }
}