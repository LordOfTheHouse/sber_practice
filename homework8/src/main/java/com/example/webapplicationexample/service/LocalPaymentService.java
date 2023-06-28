package com.example.webapplicationexample.service;

import com.example.webapplicationexample.exception.AccountNotDefined;
import com.example.webapplicationexample.exception.InsufficientFundsException;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.proxy.BankProxy;
import com.example.webapplicationexample.repository.CustomerRepository;
import com.example.webapplicationexample.repository.PromoCodesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * Сервис по оплате товаров
 */
@Service
public class LocalPaymentService implements PaymentService {
    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    public BigDecimal pay(Transfer transfer) {

        String selectSum = """
                select sum(p.price*pc.count) sum
                from katerniuksm.client c
                join katerniuksm.product_client pc on pc.id_cart = c.cart_id
                join katerniuksm.product p on p.id = pc.id_product
                where c.id = ?;
                """;

        try (var connection = DriverManager.getConnection(JDBC);
           var prepareStatement = connection.prepareStatement(selectSum)){
            prepareStatement.setLong(1, transfer.getIdUser());
            var resultProducts = prepareStatement.executeQuery();
            if(resultProducts.next()){
                double sum = resultProducts.getDouble("sum");
                if(sum!=0){
                    return BigDecimal.valueOf(sum);
                }
            }
            throw new AccountNotDefined("Нет товара для оплаты");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
