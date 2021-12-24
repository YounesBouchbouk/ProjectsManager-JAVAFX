package com.example.demo;

import com.example.demo.Listeners.ProjectListener;
import com.example.demo.models.Project;
import javafx.event.ActionEvent;
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
    private Label prjidlabel;

    private ProjectListener projectlistener;

    private Project project;

    @FXML
    private  void click(ActionEvent actionEvent){
        System.out.println("click click");
        projectlistener.PrjOnclickListener(project);
    }

    public void setData(Project p , ProjectListener prj){
        this.project=p;
        this.projectlistener = prj;
        nbtaches.setText(String.valueOf(15));
        PnameText.setText(p.getTitle());
        PrjDateD.setText(String.valueOf(p.getDate_debut()));
        prjidlabel.setText(String.valueOf(p.getId_project()));

    }


}
