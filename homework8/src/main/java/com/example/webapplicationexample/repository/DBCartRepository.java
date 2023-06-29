package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.exception.AccountNotDefined;
import com.example.webapplicationexample.exception.ProductNotInStock;
import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

/**
 * Осуществляет операции с корзиной в базе данных
 */
@Slf4j
@Repository
public class DBCartRepository implements CartRepository{
    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBCartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Cart> getCartById(long idCart){
        var selectProducts = """
                SELECT *  
                from katerniuksm.product_client
                join katerniuksm.product p on id_product = p.id
                where id_cart = ?
                """;

        var selectCast = """
                SELECT *  
                from katerniuksm.cart
                where id= ?
                """;

        PreparedStatementCreator preparedStatementCreatorProduct = connection -> {
            var prepareStatement = connection.prepareStatement(selectProducts);
            prepareStatement.setInt(1, (int) idCart);
            return prepareStatement;
        };

        PreparedStatementCreator preparedStatementCreatorCart = connection -> {
            var prepareStatement = connection.prepareStatement(selectCast);
            prepareStatement.setInt(1, (int) idCart);
            return prepareStatement;
        };

        RowMapper<Product> productsSelect = (resultSet, rowNum) -> {
            int idProduct = resultSet.getInt("id_product");
            String nameProduct = resultSet.getString("name");
            BigDecimal priceProduct = BigDecimal.valueOf(resultSet.getDouble("price"));
            int amount = resultSet.getInt("count");
            return new Product(idProduct, nameProduct, priceProduct, amount);
        };

        RowMapper<Cart> cartSelect = (resultSet, rowNum) -> {
            String promocode = resultSet.getString("promocode");
            return new Cart(idCart
                    , jdbcTemplate.query(preparedStatementCreatorProduct, productsSelect)
                    , promocode);
        };
        return jdbcTemplate.query(preparedStatementCreatorCart, cartSelect).stream().findAny();

    }

    @Override
    public boolean addProductInCartById(long cartId, Product product) {


        var insertCast = """
                insert into katerniuksm.product_client(id_product, id_cart, count)
                values(?,?,?);
                 """;
        var updateCast = """
               update katerniuksm.product_client
               set count = count + ?
               where id_cart =? and id_product=?; 
                """;
        int rows = jdbcTemplate.update(updateCast, product.getAmount(), cartId, product.getId());
        if(rows<1){
            return jdbcTemplate.update(insertCast,  product.getId(), cartId, product.getAmount()) > 0;
        }
        return true;
    }

    @Override
    public boolean updateAmountProduct(long idCart, long idProduct, int amount) {

        var updateCast = """
               update katerniuksm.product_client
               set count = ?
               where id_cart=? and id_product=?;
                """;

        return jdbcTemplate.update(updateCast, amount, idCart, idProduct) > 0;
    }

    @Override
    public boolean deleteProduct(long idCart, long idProduct) {

        var deleteProduct = """
                delete from katerniuksm.product_client
                where id_cart = ? and id_product = ?;
                """;
        int rows = jdbcTemplate.update(deleteProduct, idCart, idProduct);
        return rows > 1;
    }

    @Override
    public long generate() {
        var insertSql = """
                    INSERT INTO katerniuksm.cart (promocode) 
                    VALUES (?);
                    """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "");
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return (long) (int) keyHolder.getKeys().get("id");
    }

    @Override
    public BigDecimal getSumPriceCart(long userId) {
        String selectSum = """
                select sum(p.price*pc.count) sum
                from katerniuksm.client c
                join katerniuksm.product_client pc on pc.id_cart = c.cart_id
                join katerniuksm.product p on p.id = pc.id_product
                where c.id = ?;
                """;

        String updateAmount = """
                update katerniuksm.product p 
                set amount = amount - (select count from katerniuksm.product_client where id_product = p.id and id_cart = ?)
                where id in (select id_product from katerniuksm.product_client where id_cart = ?)
                """;

        String selectAmountProduct = """
                select count(*)
                from katerniuksm.product p
                join katerniuksm.product_client pc on p.id = pc.id_product and p.amount < pc.count
                where id_cart=?;
                """;

        String countUser = """ 
                select count(*) client
                from katerniuksm.client
                where id = ?;
                """;
        String selectPromocode = """ 
                select sum(percent)
                from katerniuksm.promocodes
                where promocode = ?;
                """;

        String selectPromocodeClient = """ 
                select c.promocode
                from katerniuksm.client
                join katerniuksm.cart c on c.id = client.cart_id
                where client.id = ?;
                """;
        Integer isAccount = jdbcTemplate.queryForObject(countUser, Integer.class, userId);
        if (isAccount < 1) {
            throw new AccountNotDefined("Аккаунта с данным id не существует");
        }
        Integer count = jdbcTemplate.queryForObject(selectAmountProduct, Integer.class, userId);
        if (count > 0) {
            throw new ProductNotInStock("Товара на складе не достаточно");
        }
        String promocode = jdbcTemplate.queryForObject(selectPromocodeClient, String.class, userId);

        Double coefficient = jdbcTemplate.queryForObject(selectPromocode, Double.class, promocode);
        if (coefficient == null) {
            coefficient = 0.0;
        } else {
            coefficient /= 100.0;
        }

        jdbcTemplate.update(updateAmount, userId, userId);
        Double sum = jdbcTemplate.queryForObject(selectSum, Double.class, userId);
        if (sum == null) {
            throw new AccountNotDefined("Нет товара для оплаты");
        }

        return BigDecimal.valueOf(sum * (1 - coefficient));
    }
}
