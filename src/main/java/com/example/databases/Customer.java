package com.example.databases;

import java.sql.*;

public class Customer {
    Connection conn;
    Statement stmt;

    private int id;
    private final String name;
    private final int phoneNumber;
    private final String postalCode;
    private final String streetName;
    private final int houseNumber;

    private boolean hasDiscount;
    private int numPizzasOrdered;

    public Customer(String name, int phoneNumber, String postalCode, String streetName, int houseNumber, Order order)
            throws SQLException, ClassNotFoundException {
        conn = DriverManager.getConnection(DataBase.DB_URL, DataBase.USER, DataBase.PASS);
        stmt = conn.createStatement();
        Class.forName(DataBase.JDBC_DRIVER);

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;

        // if customer info already exists in database
        // search for exact identical customer info in table to check
        String search = "SELECT * FROM customer WHERE name = ' " + name + " ' AND phoneNumber = " + phoneNumber +
                " AND postalCode = ' " + postalCode + " ' AND streetName = ' " + streetName + " ' AND houseNumber " +
                "= " + houseNumber;
        ResultSet rs1 = stmt.executeQuery(search);
        System.out.println(rs1.next());
        if(rs1.next()) {
            ResultSet rs = stmt.executeQuery("SELECT id, NumPizzasOrdered FROM (" + search +") AS thisCustInfo");
            while (rs.next()) {
                id = rs.getInt("id");
                numPizzasOrdered = rs.getInt("NumPizzasOrdered") + order.getNumPizzasOrdered();
                stmt.execute("UPDATE customer SET NumPizzasOrdered='" + numPizzasOrdered + "'" +
                        "WHERE id='" + id + "'");
            }
            // if customer is applicable for discount after ordering 10 pizzas
            if (numPizzasOrdered >= 10) hasDiscount = true;
            numPizzasOrdered += order.getNumPizzasOrdered();
        }

        // if customer is new to system
        // add customer info to system, where id of new customer = curr max id +1 and numPizzasOrdered = 0
        else {
            numPizzasOrdered += order.getNumPizzasOrdered();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM customer");
            if (rs.next()) id = rs.getInt("maxid") + 1;
            else           id = 1;
            // insert new customer's information into system
            stmt.execute("INSERT INTO Customer VALUES (" + id + ",'" + name + "', " + phoneNumber + ", '" +
                    postalCode + "', '" + streetName + "', " + houseNumber + ", " + numPizzasOrdered + ")");
        }
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getPhoneNumber() { return phoneNumber; }
    public String getPostalCode() { return postalCode; }
    public String getStreetName() { return streetName; }
    public int getHouseNumber() { return houseNumber; }

    public boolean hasDiscount() { return hasDiscount; }
    public void setHasDiscount(boolean hasDiscount) { this.hasDiscount = hasDiscount; }
}
