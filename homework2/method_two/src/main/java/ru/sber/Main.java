package ru.sber;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sber.conf.ConfigApp;
import ru.sber.models.Person;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ConfigApp.class);

        Person p = context.getBean(Person.class);

        System.out.println(p);


    }
}