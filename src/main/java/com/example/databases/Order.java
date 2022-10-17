package com.example.databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Order {
    Connection conn;
    Statement stmt;

    private int orderId;
    private boolean pizzaInOrder = false;
    private float totalPrice;

    public Order() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT MAX(orderId) AS maxid FROM order");
        if (rs.next()) orderId = rs.getInt("maxid") + 1;
        else           orderId = 1;
    }

    public void addPizza(int pizzaId) throws SQLException {
        // at least 1 pizza is in this order so change boolean to true
        if(!pizzaInOrder)
            pizzaInOrder = true;
        stmt.execute("INSERT INTO pizzaOrder VALUES (" + orderId + ", " + pizzaId + ")");
    }

    public void addDrink(int drinkId) throws SQLException {
        stmt.execute("INSERT INTO drinkOrder VALUES (" + orderId + ", " + drinkId + ")");
    }

    public void addDessert(int dessertId) throws SQLException {
        stmt.execute("INSERT INTO dessertOrder VALUES (" + orderId + ", " + dessertId + ")");
    }

    public boolean isPizzaInOrder() { return pizzaInOrder; }

    public float getTotalPrice() throws SQLException, ClassNotFoundException {
        float totalPrice = 0;

        ResultSet rsPizza = stmt.executeQuery("SELECT * FROM pizzaOrder");
        while (rsPizza.next())
            if (rsPizza.getInt("orderId") == this.orderId){
                Pizza pizza = new Pizza(rsPizza.getInt("pizzaId"));
                totalPrice += pizza.getPrice();
            }

        ResultSet rsDrink = stmt.executeQuery("SELECT * FROM drinkOrder");
        while (rsDrink.next())
            if (rsDrink.getInt("orderId") == this.orderId){
                Drink drink = new Drink(rsDrink.getInt("drinkId"));
                totalPrice += drink.getPrice();
            }

        ResultSet rsDessert = stmt.executeQuery("SELECT * FROM dessertOrder");
        while (rsDessert.next())
            if (rsDessert.getInt("orderId") == this.orderId){
                Dessert dessert = new Dessert(rsDessert.getInt("dessertId"));
                totalPrice += dessert.getPrice();
            }
        return totalPrice;
    }
}
