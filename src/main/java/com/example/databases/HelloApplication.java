package com.example.databases;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.databases.DataBase;

import java.io.IOException;
import java.sql.SQLException;


public class HelloApplication {
    public static DataBase dataBase;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        dataBase = new DataBase();
    }
}