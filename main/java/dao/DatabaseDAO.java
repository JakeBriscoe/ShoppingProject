/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author 432br
 */
public class DatabaseDAO implements DAOInterface {

    String connectionUri;

    public DatabaseDAO() {
        connectionUri = DbConnection.getDefaultConnectionUri();
    }

    public DatabaseDAO(String connection) {
        connectionUri = connection;
    }

    @Override
    public void deleteProduct(Product product) {
        String sql = "delete from products where productId = ?";
        
        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                
                PreparedStatement stmt = dbCon.prepareStatement(sql);
                ) {
            stmt.setString(1, product.getProductId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }        
    }

    @Override
     public Collection<String> getCategories() {
        String sql = "select distinct category from products";
        
        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                
                PreparedStatement stmt = dbCon.prepareStatement(sql);
                ) {
            
            ResultSet rs = stmt.executeQuery();
            
            List<String> categories = new ArrayList<>();
            
            while (rs.next()) {

                String category = rs.getString("category");

                categories.add(category);
            } 
            
            return categories;
            
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from products order by productId";

        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            List<Product> products = new ArrayList<>();

            while (rs.next()) {

                String productId = rs.getString("productId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

                Product product = new Product(productId, name, description,
                        category, price, quantityInStock);

                products.add(product);
            }

            return products;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void saveProduct(Product product) {
        String sql = "insert into products (productId, name, description, category, price, quantityInStock) values (?,?,?,?,?,?)";

        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, product.getProductId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setBigDecimal(5, product.getPrice());
            stmt.setBigDecimal(6, product.getQuantityInStock());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> searchCategory(String category) {
        String sql = "select * from products where category = ?";
        
        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                
                PreparedStatement stmt = dbCon.prepareStatement(sql);
                ) {
            stmt.setString(1, category);
            
            ResultSet rs = stmt.executeQuery();
            
            List<Product> products = new ArrayList<>();

            while (rs.next()) {

                String productId = rs.getString("productId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

                Product product = new Product(productId, name, description,
                        category, price, quantityInStock);

                products.add(product);
            }

            return products;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Product searchId(String id) {
        String sql = "select * from products where productId = ?";
        
        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                
                PreparedStatement stmt = dbCon.prepareStatement(sql);
                ) {
            stmt.setString(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                String productId = rs.getString("productId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

                return new Product(productId, name, description,
                        category, price, quantityInStock);
                
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}








