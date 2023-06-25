package ru.sber.repository;

import ru.sber.model.Client;
import ru.sber.model.DataForTransfer;

public interface TransferRepository {
    void insertTransferInBD(DataForTransfer dataForTransfer);
}
