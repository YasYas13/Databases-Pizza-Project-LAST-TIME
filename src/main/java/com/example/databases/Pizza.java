package com.example.databases;

import java.sql.*;
import java.util.ArrayList;

public class Pizza {
    Connection conn;
    Statement stmt;

    private int pizzaId;
    private double price;
    private int[] ingredientsIds;
    private String[] ingredientsNames;
    private boolean isVegetarian = true;
    private String name;

    public Pizza(int pizzaId) throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(DataBase.DB_URL,DataBase.USER,DataBase.PASS);
        stmt = conn.createStatement();
        Class.forName(DataBase.JDBC_DRIVER);

        this.pizzaId = pizzaId;

        ResultSet rs1 = stmt.executeQuery("SELECT pizzaId, name FROM pizza WHERE pizzaid="+pizzaId);
        while (rs1.next())
            name = rs1.getString("name");

        ArrayList<Integer> list = new ArrayList<>();
        ResultSet rs2 = stmt.executeQuery("SELECT pizzaId, ingrId FROM PizzaComposition WHERE pizzaid="+pizzaId);
        while (rs2.next())
            list.add(rs2.getInt("ingrId"));
        ingredientsIds = list.stream().mapToInt(i->i).toArray();

        ArrayList<String> list1 = new ArrayList<>();
        ResultSet rs3 = stmt.executeQuery("SELECT * FROM ingredients");
        while (rs3.next())
            // sum all ingredient prices to calculate pizza price, list all ingredient's names, determine if vega
            for (int i = 0; i < ingredientsIds.length; i++)
                if (rs3.getInt("id") == ingredientsIds[i]) {
                    price += rs3.getInt("price");
                    list1.add(rs3.getString("name"));
                    if(!rs3.getBoolean("vega"))
                        isVegetarian = false;
                }
        price = Math.round(price*1.4 *1.09 *100)/100;
        ingredientsNames = list1.toArray(new String[0]);
    }

    // getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getPizzaId() { return pizzaId; }
    public boolean isVegetarian() { return isVegetarian; }
    public int[] getIngredientsIds() { return ingredientsIds; }
    public String[] getIngredientsNames() { return ingredientsNames; }
}
