module com.example.databases {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.databases to javafx.fxml;
    exports com.example.databases;
}