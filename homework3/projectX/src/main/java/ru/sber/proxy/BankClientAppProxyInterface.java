package ru.sber.proxy;

import ru.sber.model.DataForTransfer;

public interface BankClientAppProxyInterface {
    boolean isClient(DataForTransfer dataForTransfer);
}
