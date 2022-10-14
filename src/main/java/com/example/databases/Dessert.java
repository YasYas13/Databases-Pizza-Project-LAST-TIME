package com.example.databases;

import java.sql.*;

public class Dessert {
    Connection conn = null;
    Statement stmt = null;

    private int dessertId;
    private double price;
    private String name;

    public Dessert(int dessertId) throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(DataBase.DB_URL, DataBase.USER, DataBase.PASS);
        stmt = conn.createStatement();
        Class.forName(DataBase.JDBC_DRIVER);

        this.dessertId = dessertId;

        ResultSet rs1 = stmt.executeQuery("SELECT id, name, price FROM dessert");
        while (rs1.next())
            if(rs1.getInt("id")==dessertId) {
                name = rs1.getString("name");
                price = rs1.getFloat("price");
            }
    }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getDrinkId() { return dessertId; }
}

