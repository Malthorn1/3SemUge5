/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.BankCustomer;

/**
 *
 * @author mikke
 */
public class CustomerDTO {
    BankCustomer bankCustomer = new BankCustomer() ; 
    long customerID;
    String fullName; 
    String accountNumber;
    double balance;

    public CustomerDTO() {
    }

    public CustomerDTO(BankCustomer c) {
        this.customerID = c.getId();
        this.fullName = c.getFirstName() + " " + c.getLastName() ;
        this.accountNumber = c.getAccountNumber();
        this.balance = c.getBalance();
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    
}
