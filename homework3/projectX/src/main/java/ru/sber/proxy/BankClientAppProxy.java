package ru.sber.proxy;

import org.springframework.stereotype.Component;
import ru.sber.model.Client;

import java.util.List;

/**
 * Стороннее приложение. Предоставляет информацю о клиентах банка
 */
@Component
public class BankClientAppProxy {
    private List<Client> clientList = List.of(
            new Client("Sergei", 1),
            new Client("Oleg", 2),
            new Client("Kirill", 3)
    );

    public boolean isClient(Client client) {
        for (Client cl : clientList) {
            if (cl.equals(client)) {
                System.out.println("Пользователь является клиентом банка");
                return true;
            }
        }
        System.out.println("Пользователь не является клиентом банка");
        return false;
    }

    ;
}
