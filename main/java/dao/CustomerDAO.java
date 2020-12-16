/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import web.auth.CredentialsValidator;

/**
 *
 * @author brija795
 */
public interface CustomerDAO extends CredentialsValidator {
    
    void save(Customer customer);
    
    Customer getCustomer(String username);
    
}
