package com.example.databases;

import java.sql.*;
import java.util.ArrayList;

public class Price {
    static double price;
    public double getPrice(int pizzaId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName(DataBase.JDBC_DRIVER);

        conn = DriverManager.getConnection(DataBase.DB_URL,DataBase.USER,DataBase.PASS);
        stmt = conn.createStatement();

        ArrayList<Integer> list = new ArrayList<>();
        ResultSet rs1 = stmt.executeQuery("SELECT pizzaId, ingrId FROM PizzaComposition");
        while (rs1.next()){
            if(rs1.getInt("pizzaId")==pizzaId)
                list.add(rs1.getInt("ingrId"));
        }
        ResultSet rs2 = stmt.executeQuery("SELECT id, price FROM ingredients");
        while (rs2.next()){
            for (int i = 0; i < list.size(); i++) {
                if (rs2.getInt("id")==list.get(i)) {
                    price += rs2.getInt("price");
                    break;
                }
            }
        }
        price = (double) Math.round(price*1.4 *1.09 *100)/100;
        return price;
    }

}
