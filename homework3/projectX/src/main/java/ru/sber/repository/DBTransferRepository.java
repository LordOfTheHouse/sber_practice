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
    private List<DataForTransfer> transfers = new ArrayList<>();

    public void insertTransferInBD(DataForTransfer dataForTransfer) {
        System.out.println("Информация о переводе по номеру телефона: " + dataForTransfer.getNumberPhone()
                + " на сумму: " + dataForTransfer.getSum()
                + " добавлена в базу данных"
        );
        transfers.add(dataForTransfer);
    }
}
