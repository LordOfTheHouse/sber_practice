package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Осуществляет операции с пробуктом в базе данных
 */
@Repository
public class DBProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Product product) {
        var insertSql = "INSERT INTO katerniuksm.product (name, price, amount) VALUES (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice().doubleValue());
            preparedStatement.setInt(3, product.getAmount());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return (long) (int)keyHolder.getKeys().get("id");

    }

    @Override
    public Optional<Product> findById(long productId) {
        var selectSql = """
                SELECT * FROM katerniuksm.product p 
                where id = ?
                """;

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setInt(1, (int) productId);
            return prepareStatement;
        };

        RowMapper<Product> productRowMapper = getProductRowMapper();

        List<Product> products = jdbcTemplate.query(preparedStatementCreator, productRowMapper);

        return products.stream().findFirst();
    }

    @Override
    public List<Product> findAll(String productName) {
        var selectSql = "SELECT * FROM katerniuksm.product where name like ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setString(1, "%" + (productName == null ? "" : productName) + "%");
            return prepareStatement;
        };

        RowMapper<Product> productRowMapper = getProductRowMapper();

        return jdbcTemplate.query(preparedStatementCreator, productRowMapper);
    }

    @Override
    public boolean update(Product product) {
        var updateSql = """
                UPDATE katerniuksm.product
                SET 
                name = ?,
                price = ?,
                amount = ?
                where id = ?;
                """;

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(updateSql);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setDouble(2, product.getPrice().doubleValue());
            prepareStatement.setInt(3, product.getAmount());
            prepareStatement.setLong(4, product.getId());

            return prepareStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int amount = resultSet.getInt("amount");
            return new Product(id, name, BigDecimal.valueOf(price), amount);
        };
    }

    @Override
    public boolean deleteById(long id) {
        var deleteSql = "DELETE FROM katerniuksm.product where id = ?";
        var deleteCart = "DELETE FROM katerniuksm.product_client where id_product = ?";
        jdbcTemplate.update(deleteCart, id);
        int rows = jdbcTemplate.update(deleteSql, id);
        return rows > 0;
    }
}
