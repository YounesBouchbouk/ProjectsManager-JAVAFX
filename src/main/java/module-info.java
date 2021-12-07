module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx;
    requires jfoenix;
    requires java.desktop;
    requires java.sql;
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.models;
    opens com.example.demo.models to javafx.fxml;
}