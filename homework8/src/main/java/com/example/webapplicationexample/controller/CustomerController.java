package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.CroppedCustomer;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * Управление запросами связанными с пользователем
 */
@Slf4j
@RestController
@RequestMapping("customers")
public class CustomerController {
    CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Регистрация пользователя
     * @param customer - пользователь
     * @return id зарегестрированного пользователя
     */
    @PostMapping
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        log.info("Добавление пользователя {}", customer);
        return ResponseEntity.created(URI.create("/user/"+customerRepository.registration(customer))).build();
    }

    /**
     * Возвращает пользователя по id
     * @param id - индификатор пользователя
     * @return возвращает обрезанного пользователя
     */
    @GetMapping("/{id}")
    public ResponseEntity<CroppedCustomer> getCustomer(@PathVariable Long id) {
        log.info("Поиск продуктов по id {}", id);
        Optional<Customer> customer = customerRepository.getById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok().body(new CroppedCustomer(customer.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет пользователя
     * @param id - ид пользователя
     * @return сообщение о статусе операции
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
        log.info("Удаление пользователя {}", id);
        boolean isDeleted = customerRepository.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
