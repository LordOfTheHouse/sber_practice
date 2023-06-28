package com.example.webapplicationexample.repository;

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

@Slf4j
@Repository
public class DBCustomerRepository implements CustomerRepository {
    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    private final CartRepository cartRepository;

    public DBCustomerRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public long registration(Customer customer) {
        var insertSql = """
                INSERT INTO katerniuksm.client (name, username, password, cart_id, email) 
                VALUES (?,?,?,?,?);
                """;

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setString(1, customer.getName());
            prepareStatement.setString(2, customer.getLogin());
            prepareStatement.setString(3, customer.getPassword());
            prepareStatement.setLong(4, cartRepository.generate(1L).getId());
            prepareStatement.setString(5, customer.getEmail());
            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("Ошибка при получении идентификатора");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Customer> getById(long id) {
        var selectUser = """
                SELECT *   
                FROM katerniuksm.client 
                where id = ?
                """;
        var selectCast = """
                SELECT *   
                From katerniuksm.product_client pc 
                join katerniuksm.product p on pc.id_product = p.id
                where pc.id_cart = ?
                """;

        List<Product> products = new ArrayList<>();
        try (var connection = DriverManager.getConnection(JDBC);
             var selectUserStatement = connection.prepareStatement(selectUser);
             var selectCastStatement = connection.prepareStatement(selectCast)) {

            selectUserStatement.setLong(1, id);

            var resultSet = selectUserStatement.executeQuery();

            if (resultSet.next()) {
                log.info("rabotaet 1");
                var idUser = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var email = resultSet.getString("email");
                var idCart = resultSet.getInt("cart_id");

                selectCastStatement.setLong(1, idCart);
                var resultProducts = selectCastStatement.executeQuery();
                while (resultProducts.next()) {
                    int idProduct = resultProducts.getInt("p.id");
                    String nameProduct = resultProducts.getString("p.name");
                    BigDecimal priceProduct = BigDecimal.valueOf(resultProducts.getDouble("price"));
                    int amount = resultProducts.getInt("pc.count");
                    products.add(new Product(idProduct, nameProduct, priceProduct, amount));
                }
                Cart cart = new Cart((long) idCart, products, "");
                return Optional.of(new Customer(idUser, name, "", "", email, cart));
            }
        return Optional.empty();
    } catch(SQLException e)
    {
        throw new RuntimeException(e);
    }

}

    @Override
    public boolean deleteById(long id) {

        var selectIdCart = """
                SELECT cart_id  
                FROM katerniuksm.client 
                where id = ?
                """;

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

        try (var connection = DriverManager.getConnection(JDBC);
             var selectIdCartStatement = connection.prepareStatement(selectIdCart);
             var deleteProductClientStatement = connection.prepareStatement(deleteProductClient);
             var deleteCartStatement = connection.prepareStatement(deleteCart);
             var deleteUserStatement = connection.prepareStatement(deleteUser)) {

            selectIdCartStatement.setLong(1, id);
            deleteUserStatement.setLong(1, id);


            var resultSet = selectIdCartStatement.executeQuery();

            if (resultSet.next()) {
                int cartId = resultSet.getInt("cart_id");
                deleteProductClientStatement.setLong(1, cartId);
                deleteCartStatement.setLong(1, cartId);
                deleteProductClientStatement.executeUpdate();
                int rows = deleteUserStatement.executeUpdate();
                deleteCartStatement.executeUpdate();
                return rows > 0;

            } else {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
