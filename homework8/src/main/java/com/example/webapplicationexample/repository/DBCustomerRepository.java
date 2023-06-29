package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.CroppedCustomer;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Осуществляет операции с клиентом в базе данных
 */
@Slf4j
@Repository
public class DBCustomerRepository implements CustomerRepository {
    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    private final CartRepository cartRepository;
    private final JdbcTemplate jdbcTemplate;

    public DBCustomerRepository(CartRepository cartRepository, JdbcTemplate jdbcTemplate) {
        this.cartRepository = cartRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long registration(Customer customer) {
        var insertSql = """
                INSERT INTO katerniuksm.client (name, username, password, cart_id, email) 
                VALUES (?,?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, customer.getName());
            prepareStatement.setString(2, customer.getLogin());
            prepareStatement.setString(3, customer.getPassword());
            prepareStatement.setLong(4, cartRepository.generate());
            prepareStatement.setString(5, customer.getEmail());
            return prepareStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return (long)(int) keyHolder.getKeys().get("id");
    }

    @Override
    public Optional<Customer> getById(long userId) {
        var selectUser = """
                SELECT *   
                FROM katerniuksm.client
                join katerniuksm.cart c on c.id = client.cart_id 
                where client.id = ?
                """;

        PreparedStatementCreator preparedStatementCreatorUser = connection -> {
            var prepareStatement = connection.prepareStatement(selectUser);
            prepareStatement.setInt(1, (int) userId);
            return prepareStatement;
        };

        RowMapper<Customer> userRowMapper = (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int idCart = resultSet.getInt("cart_id");
            return new Customer(id, name, null, null, email, cartRepository.getCartById(idCart).get());
        };

        return jdbcTemplate.query(preparedStatementCreatorUser, userRowMapper).stream().findAny();

    }


    @Override
    @Transactional
    public boolean deleteById(long id) {


        var deleteProductClient = """
                delete from katerniuksm.product_client
                where id_cart = ? 
                """;

        var deleteCart = """
                delete from katerniuksm.cart
                where id = ? 
                """;

        var deleteUser = """
                delete from katerniuksm.client
                where id = ? 
                """;

        jdbcTemplate.update(deleteProductClient, id);
        int rows = jdbcTemplate.update(deleteUser, id);
        jdbcTemplate.update(deleteCart, id);
        return rows > 0;

    }
}
