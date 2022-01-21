package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class about_project implements Initializable {
    @FXML
    public Label label_of_id;

    public int id ;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_of_id.setText(String.valueOf(this.id));
    }
}
