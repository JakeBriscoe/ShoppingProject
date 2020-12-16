/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author brija795
 */
public class Customer {
    
    private Integer customerId;
    @NotNull(message = "Username must be provided.")
    @NotBlank(message = "Username ID must be provided.")
    private String username;
    @NotNull(message = "First name must be provided.")
    @NotBlank(message = "First name must be provided.")
    private String firstName;
    @NotNull(message = "Surname must be provided.")
    @NotBlank(message = "Surname must be provided.")
    private String surname;
    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be provided.")
    private String password;
    @NotNull(message = "Email Address must be provided.")
    @NotBlank(message = "Email Address must be provided.")
    private String emailAddress;
    @NotNull(message = "Shipping Address must be provided.")
    @NotBlank(message = "Shipping Address must be provided.")
    private String shippingAddress;
    
    public Customer() {
    }    

    public Customer(Integer customerId, String username, String firstName, String surname, String password, String emailAddress, String shippingAddress) {
        this.customerId = customerId;
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.emailAddress = emailAddress;
        this.shippingAddress = shippingAddress;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }    
}
