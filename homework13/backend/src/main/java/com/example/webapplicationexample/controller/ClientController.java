package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.CroppedCustomer;
import com.example.webapplicationexample.model.User;
import com.example.webapplicationexample.service.CartService;
import com.example.webapplicationexample.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Управление запросами связанными с пользователем
 */
@Slf4j
@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientController {
    ClientService clientService;
    CartService cartService;

    @Autowired
    public ClientController(ClientService clientService, CartService cartService) {
        this.clientService = clientService;
        this.cartService = cartService;
    }

    /**
     * Регистрация пользователя
     * @param user - пользователь
     * @return id зарегестрированного пользователя
     */
    @PostMapping
    public ResponseEntity<?> register(@RequestBody User user) {
        log.info("Добавление пользователя {}", user);
        if(user.getUsername() == null
                || user.getEmail() == null
                || user.getUsername() == null
                || user.getPassword() == null){
            return ResponseEntity.badRequest().body("Введите данные клиента");
        }
        return ResponseEntity.ok().body(clientService.save(user));
    }

    /**
     * Возвращает пользователя по id
     * @param id - индификатор пользователя
     * @return возвращает обрезанного пользователя
     */
    @GetMapping("/{id}")
    public ResponseEntity<CroppedCustomer> getCustomer(@PathVariable Long id) {
        log.info("Поиск пользователя по id {}", id);
        Optional<User> customer = clientService.findById(id);

        if (customer.isPresent()) {
            CroppedCustomer croppedCustomer = new CroppedCustomer(customer.get());
            croppedCustomer.setCart(cartService.findProductsInCart(id));
            return ResponseEntity.ok().body(croppedCustomer);
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
        boolean isDeleted = clientService.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email")
    public ResponseEntity<CroppedCustomer> getCustomer(@RequestParam String email) {
        log.info("Поиск продуктов по email {}", email);
        Optional<User> customer = clientService.findByEmail(email);

        if (customer.isPresent()) {
            CroppedCustomer croppedCustomer = new CroppedCustomer(customer.get());
            croppedCustomer.setCart(cartService.findProductsInCart(croppedCustomer.getId()));
            return ResponseEntity.ok().body(croppedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
