package com.example.webapplicationexample.security;


import com.example.webapplicationexample.security.jwt.AuthEntryPointJwt;
import com.example.webapplicationexample.security.jwt.AuthTokenFilter;
import com.example.webapplicationexample.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    /**
     * Конфигурирует фильтр запросов.
     * Выключает csrf защиту.
     * Добавляет обработку исключений.
     * Устанавливает хранилище сессий.
     * Указывает какие запросы пропускать без аутентификации, а какие нет.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthTokenFilter authTokenFilter,
                                           AuthEntryPointJwt unauthorizedHandler,
                                           UserDetailsServiceImpl userDetailsService) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/carts/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/carts/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/promocode/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/promocode/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/payment/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/payment/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/products/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider(userDetailsService));

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Аутентифицирует пользователя.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Реализует логику аутентификации.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Шифрует пароль.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
