module com.example.databases {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.databases to javafx.fxml;
    exports com.example.databases;
}