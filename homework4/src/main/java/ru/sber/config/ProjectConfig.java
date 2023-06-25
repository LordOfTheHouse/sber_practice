package ru.sber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.sber.aspect.NotEmptyAspect;

@Configuration
@ComponentScan(basePackages = {"ru.sber.service", "ru.sber.aspect"})
@EnableAspectJAutoProxy
public class ProjectConfig {

}
