package ru.sber.repository;

import org.springframework.stereotype.Repository;
import ru.sber.model.Client;
import ru.sber.model.DataForTransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * База Данных. Хранит информацию о совершенных переводах
 */
@Repository
public class DBTransferRepository {
    private List<Map<Client, DataForTransfer>> transfers = new ArrayList<>();

    public void insertTransferInBD(Client client, DataForTransfer dataForTransfer) {
        System.out.println("Информация о переводе по номеру телефона: " + dataForTransfer.getNumberPhone()
                + " на сумму: " + dataForTransfer.getSum()
                + " добавлена в базу данных"
        );
        Map<Client, DataForTransfer> data = new HashMap<>();
        data.put(client, dataForTransfer);
        transfers.add(data);
    }
}
