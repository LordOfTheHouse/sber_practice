package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.exception.AccountNotDefined;
import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Осуществляет операции с корзиной в базе данных
 */
@Slf4j
@Repository
public class DBCartRepository implements CartRepository{
    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    @Override
    public Optional<Cart> addProductInCartById(long cartId, Product product) {
        List<Product> products = new ArrayList<>();
        var selectCast = """
                SELECT *   
                From katerniuksm.product_client pc 
                join katerniuksm.product p on pc.id_product = p.id
                where id_cart = ?
                """;
        var selectProduct = """
                SELECT *  
                From katerniuksm.product_client
                where id_cart = ? and id_product = ?
                """;
        var insertCast = """
                insert into katerniuksm.product_client(id_product, id_cart, count)
                values(?,?,?);
                 """;
        var updateCast = """
               update katerniuksm.product_client
               set count = count + ?
               where id_cart =? and id_product=?; 
                """;
        try (var connection = DriverManager.getConnection(JDBC);
             var insertProductStatement = connection.prepareStatement(insertCast, Statement.RETURN_GENERATED_KEYS);
             var selectProductInCartStatement = connection.prepareStatement(selectCast);
             var selectProductStatement = connection.prepareStatement(selectProduct);
             var updateProductStatement = connection.prepareStatement(updateCast)){
            insertProductStatement.setLong(1, product.getId());
            insertProductStatement.setLong(2, cartId);
            insertProductStatement.setInt(3, product.getAmount());
            
            selectProductStatement.setLong(1, cartId);
            selectProductStatement.setLong(2, product.getId());
            
            if(selectProductStatement.executeQuery().next()){
                updateProductStatement.setLong(1, product.getAmount());
                updateProductStatement.setLong(2, cartId);
                updateProductStatement.setLong(3, product.getId());
                updateProductStatement.executeUpdate();
            } else {
                insertProductStatement.executeUpdate();
            }

            selectProductInCartStatement.setLong(1, cartId);
            var resultProducts = selectProductInCartStatement.executeQuery();

            while (resultProducts.next()) {

                int idProduct = resultProducts.getInt("id_product");
                String nameProduct = resultProducts.getString("name");
                BigDecimal priceProduct = BigDecimal.valueOf(resultProducts.getDouble("price"));
                int amount = resultProducts.getInt("count");
                products.add(new Product(idProduct, nameProduct, priceProduct, amount));
            }
            return Optional.of(new Cart((long) cartId, products, ""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cart> updateAmountProduct(long idCart, long idProduct, int amount) {
        List<Product> products = new ArrayList<>();

        var selectCast = """
                SELECT *   
                From katerniuksm.product_client pc 
                join katerniuksm.product p on pc.id_product = p.id
                where pc.id_cart = ?
                """;
        var updateCast = """
               update katerniuksm.product_client
               set count = ?
               where id_cart=? and id_product=?;
                """;
        try (var connection = DriverManager.getConnection(JDBC);
             var selectCastStatement = connection.prepareStatement(updateCast);
             var updateCastStatement = connection.prepareStatement(selectCast)){
            selectCastStatement.setLong(1, amount);
            selectCastStatement.setLong(2, idCart);
            selectCastStatement.setLong(3, idProduct);
            selectCastStatement.executeUpdate();
            updateCastStatement.setLong(1, idCart);
            var resultProducts = updateCastStatement.executeQuery();
            while (resultProducts.next()) {
                String nameProduct = resultProducts.getString("name");
                BigDecimal priceProduct = BigDecimal.valueOf(resultProducts.getDouble("price"));
                int count = resultProducts.getInt("count");
                products.add(new Product(idProduct, nameProduct, priceProduct, count));
            }
            return Optional.of(new Cart(idCart, products, ""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProduct(long idCart, long idProduct) {

        var deleteProduct = """
                delete from katerniuksm.product_client
                where id_cart = ? and id_product = ?;
                """;
        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(deleteProduct)){
            prepareStatement.setLong(1, idCart);
            prepareStatement.setLong(2, idProduct);

            var rows = prepareStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cart generate(long id) {
        var insertSql = """
                    INSERT INTO katerniuksm.cart (promocode) 
                    VALUES (?);
                    """;
        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, "");
            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();

            if (rs.next()) {
                return new Cart(rs.getLong(1), null, null);
            } else {
                throw new RuntimeException("Ошибка при получении идентификатора");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        String countUser = """ 
                select count(*) client
                from katerniuksm.client
                where id = ?;
                """;

        try (var connection = DriverManager.getConnection(JDBC);
             var selectSumStatement = connection.prepareStatement(selectSum);
             var countUserStatement = connection.prepareStatement(countUser)){
            countUserStatement.setLong(1, userId);
            selectSumStatement.setLong(1, userId);
            var act =  countUserStatement.executeQuery();
            if(act.next()){
                int isUser = act.getInt("client");
                if(isUser == 0){
                    throw new AccountNotDefined("Пользователь не определен");
                }
            }
            var resultProducts = selectSumStatement.executeQuery();
            if(resultProducts.next()) {
                double sum = resultProducts.getDouble("sum");
                if (sum != 0) {
                    return BigDecimal.valueOf(sum);
                }
            }
            throw new AccountNotDefined("Нет товара для оплаты");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
