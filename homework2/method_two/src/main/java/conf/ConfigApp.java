package conf;

import models.Cat;
import models.Dog;
import models.Parrot;
import models.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "models")
public class ConfigApp {
    @Bean
    public Parrot parrot_one(){
        var p = new Parrot();
        p.setName("Kuk");
        return p;
    }

    @Bean
    public Parrot parrot_two(){
        var p = new Parrot();
        p.setName("Kok");
        return p;
    }

}
