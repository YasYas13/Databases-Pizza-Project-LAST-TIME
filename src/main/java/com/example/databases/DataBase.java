package com.example.databases;

import java.sql.*;

public class DataBase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/db";
    static final String USER = "Yasune";
    static final String PASS = "Y4sunesql";

    Connection conn;
    Statement stmt;

    public DataBase(boolean firstTime) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        dataBaseSetUp(firstTime);

    }

    public void dataBaseSetUp(boolean weNeedNewDB) throws SQLException {
        if(weNeedNewDB){
            stmt.execute("DROP DATABASE IF EXISTS db;");
            stmt.execute("CREATE DATABASE db;");
        }
        stmt.execute("USE db;");
        if (weNeedNewDB) {
            generateMenuItems();
            generateOrderInfo();
        }
    }

    public void generateMenuItems() throws SQLException {

        stmt.execute("CREATE TABLE IF NOT EXISTS Pizza(pizzaId INT NOT NULL PRIMARY KEY," +
                "name VARCHAR(30) NOT NULL)");
        stmt.execute("INSERT INTO pizza VALUES (1, 'pepperoni')");
        stmt.execute("INSERT INTO pizza VALUES (2, 'hawaii')");
        stmt.execute("INSERT INTO pizza VALUES (3, 'BBQ chicken')");
        stmt.execute("INSERT INTO pizza VALUES (4, '4 cheese')");
        stmt.execute("INSERT INTO pizza VALUES (5, 'tuna')");
        stmt.execute("INSERT INTO pizza VALUES (6, 'vegan veggie')");
        stmt.execute("INSERT INTO pizza VALUES (7, 'spicy salami')");
        stmt.execute("INSERT INTO pizza VALUES (8, 'vegan hawaii')");
        stmt.execute("INSERT INTO pizza VALUES (9, 'vegan BBQ')");
        stmt.execute("INSERT INTO pizza VALUES (10, 'specialty')");

        stmt.execute("CREATE TABLE IF NOT EXISTS Ingredients(id INT NOT NULL PRIMARY KEY," +
                "name VARCHAR(30) NOT NULL, price INT NOT NULL, vega BOOLEAN NOT NULL)");
        stmt.execute("INSERT INTO Ingredients VALUES (1, 'pepperoni', 3, false)");
        stmt.execute("INSERT INTO Ingredients VALUES (2, 'pineapple', 1, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (3, 'chicken', 3, false)");
        stmt.execute("INSERT INTO Ingredients VALUES (4, 'cheddar cheese', 2, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (5, 'Gouda cheese', 3, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (6, 'mozzarella cheese', 2, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (7, 'Parmesan cheese', 3, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (8, 'tuna', 3, false)");
        stmt.execute("INSERT INTO Ingredients VALUES (9, 'hot peppers', 1, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (10, 'vegan ham', 3, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (11, 'BBQ sauce', 1, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (12, 'basil', 3, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (13, 'olives', 2, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (14, 'onion', 1, true)");
        stmt.execute("INSERT INTO Ingredients VALUES (15, 'mushrooms', 2, true)");

        stmt.execute("CREATE TABLE IF NOT EXISTS PizzaComposition(compId INT NOT NULL PRIMARY KEY," +
                "pizzaId INT NOT NULL, ingrId INT NOT NULL)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(1, 1, 1)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(2, 1, 6)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(3, 2, 2)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(4, 2, 15)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(5, 2, 7)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(6, 3, 3)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(7, 3, 11)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(8, 3, 14)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(9, 4, 4)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(10, 4, 5)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(11, 4, 6)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(12, 4, 7)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(13, 5, 8)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(14, 5, 14)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(15, 6, 10)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(16, 7, 4)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(17, 7, 16)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(18, 7, 9)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(19, 8, 2)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(20, 8, 15)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(21, 8, 13)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(22, 9, 12)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(23, 9, 14)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(24, 9, 11)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(25, 10, 3)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(26, 10, 7)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(27, 10, 8)");
        stmt.execute("INSERT INTO PizzaComposition VALUES(28, 10, 15)");

        stmt.execute("CREATE TABLE IF NOT EXISTS Drink(id INT NOT NULL PRIMARY KEY, name VARCHAR(30) NOT NULL,price FLOAT NOT NULL)");
        stmt.execute("INSERT INTO Drink VALUES (1, 'regular cola',3.0)");
        stmt.execute("INSERT INTO Drink VALUES (2, 'zero cola',2.5)");
        stmt.execute("INSERT INTO Drink VALUES (3, 'sprite',3.0)");
        stmt.execute("INSERT INTO Drink VALUES (4, 'bitter lemon',2.8)");
        stmt.execute("INSERT INTO Drink VALUES (5, 'fanta',3.0)");

        stmt.execute("CREATE TABLE IF NOT EXISTS Dessert(id INT NOT NULL PRIMARY KEY, name VARCHAR(30) NOT NULL,price FLOAT NOT NULL)");
        stmt.execute("INSERT INTO Dessert VALUES (1, 'cheese cake',4.5)");
        stmt.execute("INSERT INTO Dessert VALUES (2, 'apple pie',3.8)");
        stmt.execute("INSERT INTO Dessert VALUES (3, 'milkshake',4.0)");
        stmt.execute("INSERT INTO Dessert VALUES (4, 'tiramisu',5.5)");
    }

    public void generateOrderInfo() throws SQLException {

        stmt.execute("CREATE TABLE IF NOT EXISTS PizzaOrder(orderId INT NOT NULL, pizzaId INT NOT NULL)");
        stmt.execute("CREATE TABLE IF NOT EXISTS DrinkOrder(orderId INT NOT NULL, drinkid INT NOT NULL)");
        stmt.execute("CREATE TABLE IF NOT EXISTS DessertOrder(orderId INT NOT NULL, dessertid INT NOT NULL)");

        stmt.execute("CREATE TABLE IF NOT EXISTS Customer(id INT NOT NULL PRIMARY KEY, name VARCHAR(30) NOT NULL,"+
                " PhoneNumber INT NOT NULL, PostalCode VARCHAR(6) NOT NULL, StreetName VARCHAR(30) NOT NULL,"+
                " HouseNumber INT NOT NULL, NumPizzasOrdered int)");

        stmt.execute("CREATE TABLE IF NOT EXISTS Delivery(id INT NOT NULL PRIMARY KEY, orderId INT NOT NULL)");
        stmt.execute("CREATE TABLE IF NOT EXISTS MainOrder(id INT NOT NULL PRIMARY KEY, custId INT NOT NULL," +
                "dateTime TIMESTAMP NOT NULL)");
    }

    public void executeTests() throws SQLException, ClassNotFoundException {
//        Pizza pizza1 = new Pizza(1);
//        System.out.println("Pizza name: "+pizza1.getName());
//        System.out.println("price "+pizza1.getPrice());
//        System.out.println("Is vegetarian? "+ pizza1.isVegetarian());
//        System.out.println("Ingredients:");
//        for (int i = 0; i < pizza1.getIngredientsNames().length; i++)
//            System.out.print(pizza1.getIngredientsNames()[i]+" ");
//        Dessert dessert = new Dessert(3);
//        System.out.println(dessert.getName());
//        System.out.println(dessert.getPrice());

            //stmt.execute("INSERT INTO Customer VALUES (1, 'Yasune', 06123, '6228GJ', 'Holdaal', 45, 0)");
//        ResultSet rs1 = stmt.executeQuery("SELECT * FROM pizza WHERE pizzaid=(SELECT MAX(pizzaid) FROM pizza)");
//        System.out.println(rs1.getInt("pizzaid"));
//        Customer yas = new Customer("yasune", 6123, "6228gj", "holdaal", 45);
//        System.out.println(yas.getNumPizzasOrdered());
    }

    public int getMaxPizzaId() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT MAX(pizzaid) AS maxid FROM pizza");
        if (rs.next()) return rs.getInt("maxid");
        return 0;
    }
    public int getMaxDrinkId() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM drink");
        if (rs.next()) return rs.getInt("maxid");
        return 0;
    }
    public int getMaxDessertId() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM dessert");
        if (rs.next()) return rs.getInt("maxid");
        return 0;
    }
}
