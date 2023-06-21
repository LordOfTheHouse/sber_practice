package ru.sber.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация
 */
@Configuration
@ComponentScan(basePackages = {"ru.sber.service", "ru.sber.repository", "ru.sber.proxy"})
public class AppConfig {
}
