package com.example.databases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Order {
    Connection conn;
    Statement stmt;

    private int orderId;
    private boolean pizzaInOrder = false;
    private float totalPrice;

    // 0-10 = number of pizzas ordered
    // 11 = discount after ordering 10 pizzas is used already, stop counting after 11
    private int numPizzasOrdered = 0;

    private boolean isOrderOutForDelivery = false;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime timeConfirmed;

    public Order() throws SQLException, ClassNotFoundException {
        conn = DriverManager.getConnection(DataBase.DB_URL,DataBase.USER,DataBase.PASS);
        stmt = conn.createStatement();
        Class.forName(DataBase.JDBC_DRIVER);

        try {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM mainorder");
            orderId = rs.getInt("maxid") + 1;
        }
        catch (SQLException e) { orderId = 1; }
    }

    public void addPizza(int pizzaId) throws SQLException {
        numPizzasOrdered++;
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

        ResultSet rsPizza = stmt.executeQuery("SELECT * FROM pizzaOrder WHERE orderid="+this.orderId);
        while (rsPizza.next()) {
            Pizza pizza = new Pizza(rsPizza.getInt("pizzaId"));
            totalPrice += pizza.getPrice();
        }
        ResultSet rsDrink = stmt.executeQuery("SELECT * FROM drinkOrder WHERE orderid="+this.orderId);
        while (rsDrink.next()) {
            Drink drink = new Drink(rsDrink.getInt("drinkId"));
            totalPrice += drink.getPrice();
        }
        ResultSet rsDessert = stmt.executeQuery("SELECT * FROM dessertOrder WHERE orderid="+this.orderId);
        while (rsDessert.next()) {
            Dessert dessert = new Dessert(rsDessert.getInt("dessertId"));
            totalPrice += dessert.getPrice();
        }
        return totalPrice;
    }
    Timer timer;
    public void confirmOrder() throws SQLException {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Order is out for delivery!");
            }
        };
        timer.schedule(timerTask, 3000);
        timeConfirmed = LocalDateTime.now();

    }
    public String[] getOrderedItems() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rsPizza = stmt.executeQuery("SELECT pizzaid FROM pizzaorder WHERE orderid="+this.orderId);
        while (rsPizza.next())
            list.add(new Pizza(rsPizza.getInt("pizzaid")).getName());
        ResultSet rsDrink = stmt.executeQuery("SELECT drinkId FROM drinkOrder WHERE orderid="+this.orderId);
        while (rsDrink.next())
            list.add(new Drink(rsDrink.getInt("drinkId")).getName());
        ResultSet rsDessert = stmt.executeQuery("SELECT dessertId FROM dessertOrder WHERE orderid="+this.orderId);
        while (rsDessert.next())
            list.add(new Dessert(rsDessert.getInt("dessertId")).getName());
        return list.toArray(new String[0]);
    }

    public int getNumPizzasOrdered() {
        return numPizzasOrdered;
    }
}
