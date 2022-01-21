package com.example.demo;

import com.example.demo.models.Project;
import com.example.demo.models.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TaskesGest implements Initializable {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet userLogedin = null;
    Connection con = null;


    @FXML
    private AnchorPane taskespan;

    int idproject;
    String query = "select * from javafx2.tache where participerid = ? and projetid = ?";
    @FXML
    Label idlabel;

    public  void setIdproject(int id ){

        idproject = id;
        idlabel.setText(String.valueOf(idproject));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            preparedStatement = con.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(0 , UserSession.instance.getID());
            preparedStatement.setInt(1 , idproject);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
