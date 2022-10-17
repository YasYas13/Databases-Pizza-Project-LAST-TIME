package com.example.databases;

import java.sql.*;

public class Drink {
    Connection conn;
    Statement stmt;

    private int drinkId;
    private float price;
    private String name;



    public Drink(int drinkId) throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(DataBase.DB_URL, DataBase.USER, DataBase.PASS);
        stmt = conn.createStatement();
        Class.forName(DataBase.JDBC_DRIVER);

        this.drinkId = drinkId;

        ResultSet rs1 = stmt.executeQuery("SELECT id, name, price FROM drink WHERE id="+drinkId);
        while (rs1.next()){
                name = rs1.getString("name");
                price = rs1.getFloat("price");
        }
    }

    // getters
    public String getName() { return name; }
    public float getPrice() { return price; }
    public int getDrinkId() { return drinkId; }
}

