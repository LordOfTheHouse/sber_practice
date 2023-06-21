package ru.sber.proxy;

import org.springframework.stereotype.Component;
import ru.sber.model.Client;
import ru.sber.model.DataForTransfer;

/**
 * Стороннее приложение. Переводит денежные средства по номеру телефона
 */
@Component
public class TransferByPhoneAppProxy {
    public void transferSum(DataForTransfer dataForTransfer) {
        System.out.println("По номеру телефона: " + dataForTransfer.getNumberPhone()
                + " совершен перевод на сумму: " + dataForTransfer.getSum()
        );
    }
}
