/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedrojhonathangonzalesculque
 */
public class ProductDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    private static final String FIELD_COLUMN_NOME = "name";
    private static final String FIELD_COLUMN_DESCRICAO = "description";
    private static final String FIELD_COLUMN_PRECO_COMPRA = "purchase_price";
    private static final String FIELD_COLUMN_PRECO_VENDA = "price_sale";
    private static final String FIELD_COLUMN_QUANTIDADE = "amount";

    public ProductDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (" + FIELD_COLUMN_NOME + ", " + FIELD_COLUMN_DESCRICAO + ", " + FIELD_COLUMN_PRECO_COMPRA + ", " + FIELD_COLUMN_PRECO_VENDA + ", " + FIELD_COLUMN_QUANTIDADE + ") VALUES (?, ?, ?,?,?)";
        connect();

        boolean rowInserted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPurchasePrice());
            statement.setDouble(4, product.getSalePrice());
            statement.setInt(5, product.getAmount());
            rowInserted = statement.executeUpdate() > 0;
        }
        disconnect();
        return rowInserted;
    }

    public List<Product> listAllProducts() throws SQLException {
        List<Product> listProduct = new ArrayList<>();

        String sql = "SELECT * FROM product";

        connect();

        try (Statement statement = jdbcConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString(FIELD_COLUMN_NOME);
                String description = resultSet.getString(FIELD_COLUMN_DESCRICAO);
                double purchasePrice = resultSet.getDouble(FIELD_COLUMN_PRECO_COMPRA);
                double salePrice = resultSet.getDouble(FIELD_COLUMN_PRECO_VENDA);
                int amount = resultSet.getInt(FIELD_COLUMN_QUANTIDADE);
// Product(int id, String name, String description, double purchasePrice, double salePrice, int amount) {

//    public Product(String name, String description, double purchasePrice, double salePrice, int amount, String categorie) {
                Product product = new Product(id, name, description, purchasePrice, salePrice, amount);
                listProduct.add(product);
            }

            resultSet.close();
        }

        disconnect();

        return listProduct;
    }

    public boolean deleteProduct(Product product) throws SQLException {
        String sql = "DELETE FROM product where id = ?";

        connect();

        boolean rowDeleted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            rowDeleted = statement.executeUpdate() > 0;
        }
        disconnect();
        return rowDeleted;
    }

    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET name = ?, description = ?, purchase_price = ?, price_sale = ?, amount = ?";
        sql += " WHERE id = ?";
        connect();

        boolean rowUpdated;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPurchasePrice());
            statement.setDouble(4, product.getSalePrice());
            statement.setInt(5, product.getAmount());
            statement.setInt(6, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        disconnect();
        return rowUpdated;
    }

    public Product getProduct(int id) throws SQLException {
        Product product = null;
        String sql = "SELECT * FROM product WHERE id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(FIELD_COLUMN_NOME);
                    int idProduct = resultSet.getInt("id");

                    String description = resultSet.getString(FIELD_COLUMN_DESCRICAO);
                    double purchasePrice = resultSet.getDouble(FIELD_COLUMN_PRECO_COMPRA);
                    double salePrice = resultSet.getDouble(FIELD_COLUMN_PRECO_VENDA);
                    int amount = resultSet.getInt(FIELD_COLUMN_QUANTIDADE);
                    product = new Product(idProduct, name, description, purchasePrice, salePrice, amount);
                }
            }
        }

        return product;
    }
}
