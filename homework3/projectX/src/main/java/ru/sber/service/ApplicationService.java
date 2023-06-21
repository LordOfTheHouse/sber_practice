package ru.sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.exception.UserIsNotAClientOfTheBank;
import ru.sber.model.Client;
import ru.sber.model.DataForTransfer;
import ru.sber.proxy.BankClientAppProxy;
import ru.sber.proxy.TransferByPhoneAppProxy;
import ru.sber.repository.DBTransferRepository;

@Service
public class ApplicationService {

    private BankClientAppProxy bankClientAppProxy;
    private TransferByPhoneAppProxy transferByPhoneAppProxy;
    private DBTransferRepository transferRepository;

    @Autowired
    public ApplicationService(BankClientAppProxy bankClientAppProxy,
                              TransferByPhoneAppProxy transferByPhoneAppProxy,
                              DBTransferRepository transferRepository) {
        this.bankClientAppProxy = bankClientAppProxy;
        this.transferByPhoneAppProxy = transferByPhoneAppProxy;
        this.transferRepository = transferRepository;
    }

    public void transferPayment(Client client, DataForTransfer dataForTransfer)
            throws UserIsNotAClientOfTheBank {
        if (!bankClientAppProxy.isClient(client)) {
            throw new UserIsNotAClientOfTheBank("Пользователь не является клиентом банка");
        }
        transferByPhoneAppProxy.transferSum(client, dataForTransfer);
        transferRepository.insertTransferInBD(client, dataForTransfer);
    }

}