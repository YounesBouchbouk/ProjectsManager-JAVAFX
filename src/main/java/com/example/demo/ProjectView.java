package com.example.demo;

import com.example.demo.models.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ProjectView {
    @FXML
    private Label nbtaches;

    @FXML
    private Label PnameText;

    @FXML
    private Label PrjDateD;

    @FXML
    private Button prjbtn;

    public void setData(Project p){
        nbtaches.setText(String.valueOf(33));
        PnameText.setText(p.getTitle());
        PrjDateD.setText(String.valueOf(p.getDate_debut()));

    }


}
