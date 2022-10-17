package com.example.databases;

import java.sql.*;

public class Dessert {
    Connection conn;
    Statement stmt;

    private int dessertId;
    private float price;
    private String name;

    public Dessert(int dessertId) throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(DataBase.DB_URL, DataBase.USER, DataBase.PASS);
        stmt = conn.createStatement();
        Class.forName(DataBase.JDBC_DRIVER);

        this.dessertId = dessertId;

        ResultSet rs1 = stmt.executeQuery("SELECT id, name, price FROM dessert WHERE id="+dessertId);
        while (rs1.next()) {
            name = rs1.getString("name");
            price = rs1.getFloat("price");
        }
    }

    // getters
    public String getName() { return name; }
    public float getPrice() { return price; }
    public int getDessertId() { return dessertId; }
}

