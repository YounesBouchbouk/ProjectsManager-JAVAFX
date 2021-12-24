package com.example.demo;

import com.example.demo.Listeners.ProjectListener;
import com.example.demo.models.Project;
import com.example.demo.models.UserSession;
import com.example.demo.models.db;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public  class relationControllers implements Initializable {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet userLogedin = null;
    Connection con = null;

    private ProjectListener prjlisten;

    @FXML
    private Text sessionName;

    @FXML
    private Text sessionId;

    @FXML
    private AnchorPane SearchProject;

    @FXML
    private HBox addTasks;

    @FXML
    private HBox addproject;

    @FXML
    private HBox dashboard;

    @FXML
    private ScrollPane listeproject;


    @FXML
    private Button dashboardmenubtn;

    @FXML
    private Button projectlistebtn;

    @FXML
    private Button addprj;

    @FXML
    private Button settingsmenubtn;

    @FXML
    private Button taskesmenubtn;

    @FXML
    private Button teammenubtn;

    @FXML
    private Text pathfiletxt;

    //========================================================AddProject Fields
    @FXML
    private Label LabelOfPrj;

    @FXML
    private TextField AddCategorieprj;

    @FXML
    private TextField AddClientprj;

    @FXML
    private DatePicker AddDBprj;

    @FXML
    private DatePicker AddDFprj;

    @FXML
    private TextField AddDescriptionprj;

    @FXML
    private TextField AddTitleprj;

    //==========================================================Gridpane
    @FXML
    private  GridPane projectGrid;
    private List<Project> posts =  new ArrayList<>();

    private void LabelOfPrjError(Color color, String text) {
        LabelOfPrj.setTextFill(color);
        LabelOfPrj.setText(text);
        System.out.println(text);
    }


    public void addproject(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

        String title = AddTitleprj.getText();
        String Description = AddDescriptionprj.getText();
        String Categorie = AddCategorieprj.getText();
        String Client = AddClientprj.getText();
        String Datedb = (AddDBprj.getValue()).format(formatter);
        String datefn = (AddDFprj.getValue()).format(formatter);

        if(title=="" || Description=="" || Categorie=="" || Client=="" || Datedb=="" || datefn==""){
            System.out.println("Ta 3mr lna kolchi");

        }else {
            /*con = db.getConnection();
            String Query ="insert into javafx2.project values(NULL , ?  , ? , ? , ?, ? , ? , ? , 0 )";*/
            String QueryTeam = "insert into javafx2.team values(NULL , 3)";
            con = db.getConnection();
            try {
                preparedStatement = con.prepareStatement(QueryTeam , Statement.RETURN_GENERATED_KEYS);
                int rows = preparedStatement.executeUpdate();
                int Idteam=0 ;
                if(rows == 1){
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if(rs.next())
                        Idteam = rs.getInt(1);
                    if(Idteam != 0){
                        System.out.println("hanta ha id dial team jdide");
                        System.out.println(Idteam);
                        String participedQuery = "insert into javafx2.participed values(?,?,?,?,?)";
                        preparedStatement = con.prepareStatement(participedQuery , Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1 , "Designer");
                        preparedStatement.setString(2 , "A");
                        preparedStatement.setInt(3 , 1);
                        preparedStatement.setInt(4 , Idteam);
                        preparedStatement.setInt(5,UserSession.instance.getID());

                        rows = preparedStatement.executeUpdate();
                        if(rows == 1)
                        {
                            System.out.println("participed nadia ");
                            String projectQuery = "insert into javafx2.project values(NULL,?,?,?,?,?,?,?,?,?)";
                            preparedStatement = con.prepareStatement(projectQuery , Statement.RETURN_GENERATED_KEYS);
                            preparedStatement.setString(1,title);
                            preparedStatement.setString(2,Description);
                            preparedStatement.setString(3,Datedb);
                            preparedStatement.setString(4,datefn);
                            preparedStatement.setString(5,"P");
                            preparedStatement.setString(6,Client);
                            preparedStatement.setString(7,Categorie);
                            preparedStatement.setInt(8,Idteam);
                            preparedStatement.setInt(9,UserSession.instance.getID());
                            int rows2 = preparedStatement.executeUpdate();
                            if(rows2 !=0){
                                System.out.println("Kolchi nadi");
                                int ProjectId = 0;
                                ResultSet rs2 = preparedStatement.getGeneratedKeys();
                                if(rs2.next())
                                    ProjectId = rs2.getInt(1);
                                String Full = "Successful ..... ProjectId = " + ProjectId + " !" ;
                                LabelOfPrjError(Color.GREEN , Full);
                                fillout();
                                System.out.println("hanhna drna reload");
                            }
                        }else{
                            System.out.println("mochkila f participed");
                            LabelOfPrjError(Color.RED , "Something went wrong ! ");
                        }
                    }else {
                        System.out.println("mochkila f id dial team jdide");
                        LabelOfPrjError(Color.RED , "Something went wrong ! ");
                    }

                }


            } catch (SQLException e) {
                e.printStackTrace();
                LabelOfPrjError(Color.RED , "Something went wrong ! ");
            }


        }
    }



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
    } else if (addprj.equals(source)) {
        pathfiletxt.setText("/dashboard/project/AddProject");
        SearchProject.setVisible(false);
        dashboard.setVisible(false);
        listeproject.setVisible(false);
        addTasks.setVisible(false);
        addproject.setVisible(true);
        addproject.toFront();
    }

}

    @FXML
    private void CloseApp(ActionEvent event){
        System.exit(0);
    }

    @FXML
    public  void fillout(){

        try {

            posts = data();
            int colums = 0;
            int rows = 1 ;


            for(int i = 0 ; i < posts.size() ; i++ ){
                FXMLLoader projectview = new FXMLLoader();
                projectview.setLocation(getClass().getResource("Projects.fxml"));

                Pane projectpane = projectview.load();

                ProjectView prjv = projectview.getController();
                prjv.setData(posts.get(i),prjlisten);

                if(colums == 3 ){
                    colums = 0;
                    ++rows;
                }

                projectGrid.add(projectpane , colums++ ,rows );

                GridPane.setMargin(projectpane ,new Insets(10));

            }


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sessiion configuration

        sessionName.setText(UserSession.instance.getUserName());
        sessionId.setText(String.valueOf(UserSession.instance.getID()));
        System.out.println("Ara hna");

        prjlisten = new ProjectListener() {
            @Override
            public void PrjOnclickListener(Project project) {
                System.out.println(project.getTitle());
            }
        };

        fillout();

    }

    public  List<Project> data() throws ParseException {
        System.out.println("hani kangad fdata");
        con = db.getConnection();
        String participedQuery = "Select * from project where  idchef = ?";

        List<Project> ls = new ArrayList<>();
        Project p = new Project();
        String sDate1;
        Date date1;


        try {
            preparedStatement = con.prepareStatement(participedQuery , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1 , UserSession.instance.getID());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(10));
                System.out.println("checki lblan ");
                p= new Project();
                System.out.println(String.valueOf(resultSet.getDate(4)));
                //sDate1="31/12/2029";
                sDate1= String.valueOf(resultSet.getDate(4));
                date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                p.setDate_debut(date1);
                p.setTitle(resultSet.getString(2));
                p.setId_project(resultSet.getInt(1));
                ls.add(p);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ls ;

    }
}
