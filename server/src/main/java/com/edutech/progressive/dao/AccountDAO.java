package com.edutech.progressive.dao;


import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.entity.Accounts;

public interface AccountDAO {
   int addAccount(Accounts accounts) throws SQLException;
    Accounts getAccountById(int accountId) throws SQLException;
    void updateAccount(Accounts accounts) throws SQLException;
    void deleteAccount(int accountId) throws SQLException;
    List<Accounts> getAllAccounts() throws SQLException;
    List<Accounts> getAllAccountsByCustomer(int customerId) throws SQLException;
}

/*
CREATE TABLE customers (
customer_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
role VARCHAR(255));

CREATE TABLE accounts (
account_id INT AUTO_INCREMENT PRIMARY KEY,
customer_id INT NOT NULL,
balance DECIMAL(10, 2) NOT NULL);

CREATE TABLE transactions (
transaction_id INT AUTO_INCREMENT PRIMARY KEY,
account_id INT NOT NULL,
amount DECIMAL(10, 2) NOT NULL,
transaction_date TIMESTAMP NOT NULL,
transaction_type VARCHAR(255) NOT NULL
):
 */