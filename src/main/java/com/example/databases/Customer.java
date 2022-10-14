package com.example.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
    Connection conn = null;
    Statement stmt = null;

    private String name;
    private int phoneNumber;
    private String postalCode;
    private String streetName;
    private int houseNumber;

    public Customer(String name, int phoneNumber, String postalCode, String streetName, int houseNumber) throws SQLException, ClassNotFoundException {
        conn = DriverManager.getConnection(DataBase.DB_URL, DataBase.USER, DataBase.PASS);
        stmt = conn.createStatement();
        Class.forName(DataBase.JDBC_DRIVER);

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;

    }
}
