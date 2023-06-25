package ru.sber.service;

import ru.sber.exception.UserIsNotAClientOfTheBank;
import ru.sber.model.DataForTransfer;

public interface TransferServiceInterface {
    void transferPayment(DataForTransfer dataForTransfer)
            throws UserIsNotAClientOfTheBank;
}
