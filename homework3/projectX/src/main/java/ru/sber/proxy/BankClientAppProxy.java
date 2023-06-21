package ru.sber.proxy;

import org.springframework.stereotype.Component;
import ru.sber.model.Client;
import ru.sber.model.DataForTransfer;

import java.util.List;

/**
 * Стороннее приложение. Предоставляет информацю о клиентах банка
 */
@Component
public class BankClientAppProxy {
    private List<Client> clientList = List.of(
            new Client("Sergei", 1, "89535121588"),
            new Client("Oleg", 2, "89535121587"),
            new Client("Kirill", 3, "89535121586")
    );

    public boolean isClient(DataForTransfer dataForTransfer) {
        for (Client cl : clientList) {
            if (cl.getPhoneNumber().equals(dataForTransfer.getNumberPhone())) {
                System.out.println("Пользователь является клиентом банка");
                return true;
            }
        }
        System.out.println("Пользователь не является клиентом банка");
        return false;
    }

    ;
}
