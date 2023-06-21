package ru.sber.proxy;

import org.springframework.stereotype.Component;
import ru.sber.model.Client;
import ru.sber.model.DataForTransfer;

@Component
public class TransferByPhoneAppProxy {
    public void transferSum(Client client, DataForTransfer dataForTransfer) {
        System.out.println("Клиенту: " + client.getName()
                + " по номеру телефона: " + dataForTransfer.getNumberPhone()
                + " совершен перевод на сумму: " + dataForTransfer.getSum()
        );
    }
}
