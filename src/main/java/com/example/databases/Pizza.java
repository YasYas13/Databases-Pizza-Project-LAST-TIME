package com.example.databases;

import java.sql.*;
import java.util.ArrayList;

public class Pizza {
    Connection conn = null;
    Statement stmt = null;

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

        ResultSet rs1 = stmt.executeQuery("SELECT pizzaId, name FROM pizza");
        while (rs1.next())
            if(rs1.getInt("pizzaId")==pizzaId)
                name = rs1.getString("name");

        ArrayList<Integer> list = new ArrayList<>();
        ResultSet rs2 = stmt.executeQuery("SELECT pizzaId, ingrId FROM PizzaComposition");
        while (rs2.next())
            if(rs2.getInt("pizzaId")==pizzaId)
                list.add(rs2.getInt("ingrId"));
        ingredientsIds = list.stream().mapToInt(i->i).toArray();

        ArrayList<String> list1 = new ArrayList<>();
        ResultSet rs3 = stmt.executeQuery("SELECT id, price, name, vega FROM ingredients");
        while (rs3.next())
            for (int i = 0; i < ingredientsIds.length; i++)
                if (rs3.getInt("id") == ingredientsIds[i]) {
                    price += rs3.getInt("price");
                    list1.add(rs3.getString("name"));
                    if(rs3.getBoolean("vega") == false)
                        isVegetarian = false;
                }

        price = Math.round(price*1.4 *1.09 *100)/100;
        ingredientsNames = list1.toArray(new String[0]);
    }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getPizzaId() { return pizzaId; }

    public boolean isVegetarian() { return isVegetarian; }

    public int[] getIngredientsIds() { return ingredientsIds; }

    public String[] getIngredientsNames() { return ingredientsNames; }
}
