package conf;

import models.Cat;
import models.Dog;
import models.Parrot;
import models.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {
    @Bean
    public Cat cat(){
        var c = new Cat();
        c.setName("Vasya");
        return c;
    }
    @Bean
    public Dog dog(){
        var d = new Dog();
        d.setName("Sharik");
        return d;
    }

    @Bean
    public Parrot parrotOne(){
        var p = new Parrot();
        p.setName("Kuk");
        return p;
    }

    @Bean
    public Parrot parrotTwo(){
        var p = new Parrot();
        p.setName("Kok");
        return p;
    }

    @Bean
    public Person person(){
        var p = new Person(cat(), dog(), parrotOne(), parrotTwo());
        p.setName("Oleg");
        return p;
    }
}
