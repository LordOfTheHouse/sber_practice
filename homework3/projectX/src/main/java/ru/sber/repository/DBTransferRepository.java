package ru.sber.repository;

import org.springframework.stereotype.Repository;
import ru.sber.model.DataForTransfer;

import java.util.ArrayList;
import java.util.List;


/**
 * База Данных. Хранит информацию о совершенных переводах
 */
@Repository
public class DBTransferRepository implements TransferRepository{
    private List<DataForTransfer> transfers = new ArrayList<>();

    public void insertTransferInBD(DataForTransfer dataForTransfer) {
        System.out.println("Информация о переводе по номеру телефона: " + dataForTransfer.getNumberPhone()
                + " на сумму: " + dataForTransfer.getSum()
                + " добавлена в базу данных"
        );
        transfers.add(dataForTransfer);
    }
}
