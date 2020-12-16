/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author brija795
 */
public class CustomerDatabaseDAO implements CustomerDAO {
    
    String connectionUri;

    public CustomerDatabaseDAO() {
        connectionUri = DbConnection.getDefaultConnectionUri();
    }

    public CustomerDatabaseDAO(String connection) {
        connectionUri = connection;
    }
    
    @Override
    public void save(Customer customer) {
        String sql = "insert into customers (username, firstName, surname, password, emailAddress, shippingAddress) values (?,?,?,?,?,?)";

        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getEmailAddress());
            stmt.setString(6, customer.getShippingAddress());
            
            stmt.executeUpdate();
           
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public Customer getCustomer(String username) {
        String sql = "select * from customers where username = ?";
        
        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                
                PreparedStatement stmt = dbCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                    
                int customerId = rs.getInt("customerId");
                String firstName = rs.getString("firstName");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                String emailAddress = rs.getString("emailAddress");
                String shippingAddress = rs.getString("shippingAddress");

                return new Customer(customerId, username, firstName, surname,
                    password, emailAddress, shippingAddress);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public Boolean validateCredentials(String username, String password){
        String sql = "select * from customers where username = ?";
        
        try (
                Connection dbCon = DbConnection.getConnection(connectionUri);
                
                PreparedStatement stmt = dbCon.prepareStatement(sql);
                ) {
            
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {

                String passwordDatabase = rs.getString("password");

                return password.equals(passwordDatabase);
                
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
}
