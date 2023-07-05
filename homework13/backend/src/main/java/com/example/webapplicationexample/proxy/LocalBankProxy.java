package com.example.webapplicationexample.proxy;

import com.example.webapplicationexample.exception.AccountNotDefined;
import com.example.webapplicationexample.exception.InsufficientFundsException;
import com.example.webapplicationexample.model.BankAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Банк пользователя
 */
@Component
public class LocalBankProxy implements BankProxy {
    List<BankAccount> accountList = List.of(new BankAccount("1111", BigDecimal.valueOf(1000000000)),
            new BankAccount("123412142", BigDecimal.valueOf(100)));
    private static final int ACCEPT = 1;
    @Override
    public BigDecimal checkMeansCustomer(String numberCard, BigDecimal sum) {
        Optional<BankAccount> account = accountList
                .stream()
                .filter(bankAccount -> bankAccount.getNumberBankCard().equals(numberCard))
                .findAny();
        if(account.isEmpty()) {
            throw new AccountNotDefined("Аккаунт не найден в базе данных банка");
        }
        if (account.get().getSum().compareTo(sum) == ACCEPT) {
            return sum;
        } else {
            throw new InsufficientFundsException("Недостаточно средств");
        }
    }
}
