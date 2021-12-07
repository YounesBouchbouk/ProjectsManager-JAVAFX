package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public  class relationControllers {

    @FXML
    private AnchorPane SearchProject;

    @FXML
    private HBox addTasks;

    @FXML
    private HBox addproject;

    @FXML
    private HBox dashboard;

    @FXML
    private VBox listeproject;


    @FXML
    private Button dashboardmenubtn;

    @FXML
    private Button projectlistebtn;

    @FXML
    private Button settingsmenubtn;

    @FXML
    private Button taskesmenubtn;

    @FXML
    private Button teammenubtn;

    @FXML
    private Text pathfiletxt;



    public void HandlClick(ActionEvent event ){

    Object source = event.getSource();
    if (dashboardmenubtn.equals(source)) {
        pathfiletxt.setText("/dashboard");
        SearchProject.setVisible(false);
        addTasks.setVisible(false);
        addproject.setVisible(false);
        listeproject.setVisible(false);
        dashboard.setVisible(true);
        dashboard.toFront();
        System.out.println("salam");
    } else if (projectlistebtn.equals(source)) {
        pathfiletxt.setText("/dashboard/My project");
        SearchProject.setVisible(false);
        addTasks.setVisible(false);
        addproject.setVisible(false);
        dashboard.setVisible(false);
        listeproject.setVisible(true);
        listeproject.toFront();
        System.out.println("salam2");
    } else if (settingsmenubtn.equals(source)) {
        pathfiletxt.setText("/dashboard/user/Setting");
        SearchProject.setVisible(false);
        addTasks.setVisible(false);
        addproject.setVisible(false);
        dashboard.setVisible(false);
        listeproject.setVisible(true);
        listeproject.toFront();
    } else if (taskesmenubtn.equals(source)) {
        pathfiletxt.setText("/dashboard/project/Addtask");
        SearchProject.setVisible(false);
        addproject.setVisible(false);
        dashboard.setVisible(false);
        listeproject.setVisible(false);
        addTasks.setVisible(true);
        addTasks.toFront();
    } else if (teammenubtn.equals(source)) {
        pathfiletxt.setText("/dashboard/project/Jointeam");

        addproject.setVisible(false);
        dashboard.setVisible(false);
        listeproject.setVisible(false);
        addTasks.setVisible(false);
        SearchProject.setVisible(true);
        SearchProject.toFront();
    }

}

    @FXML
    private void CloseApp(ActionEvent event){
        System.exit(0);
    }




}
