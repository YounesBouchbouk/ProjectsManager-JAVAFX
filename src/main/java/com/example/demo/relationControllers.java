package com.example.demo;

import com.example.demo.Listeners.ProjectListener;
import com.example.demo.models.Project;
import com.example.demo.models.UserSession;
import com.example.demo.models.db;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.*;

public  class relationControllers implements Initializable {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet userLogedin = null;
    Connection con = null;

    //project deaile anchore pane
    @FXML
    private AnchorPane detailanchorpane;
    @FXML
    private Label Dtchefprj;

    @FXML
    private Label DtprjId;

    @FXML
    private Label Dtprjdesc;

    @FXML
    private Label Dtprjtitle;

    @FXML
    private Label Dtrole;

    @FXML
    private Button indevBtnTaskes;


    ///endes of it


    private ProjectListener prjlisten;

    @FXML
    private Text sessionName;

    @FXML
    private Text sessionId;

    @FXML
    private AnchorPane SearchProject;


    @FXML
    private HBox addproject;

    @FXML
    private VBox dashboard;

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
    //------------------------------------Dashbord

    @FXML
    private Label label1;
    private volatile boolean stop = false;

    @FXML
    private Label label2 ;


    //////////////////////
    @FXML
    private Pane settings;

    @FXML
    private Text l3;
    @FXML
    private Text l2;
    @FXML
    private Text l1;

    @FXML
    private Text nbtask_label1;

    @FXML
    private Text nb_done_taskes;

    @FXML
    private Text nb_new_taskes;

    @FXML
    private Text nb_todo_taskes;




    ////////////////////////--Dasboard---------//////////////////////////////////////////////////////////////////////////////////////////////
    // time in dashbord
    private void Timenow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            while (!stop) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    label1.setText(timenow);
                });
            }
        });
        thread.start();
    }

    //date in dashbord
    private void datenow() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
        String datenow = sdf.format(new Date());
        label2.setText(datenow);

    }
///////////////////////////////////End here/////////////////////////////////////////////////////////

    /////:/:/ EDITE PROFILS //////////////////////////////////////

    @FXML
    private TextField Fullname;
    @FXML
    private TextField temail;
    @FXML
    private PasswordField paswd;

    @FXML
    private PasswordField cpaswd;
    @FXML
    private Button btn2;
    @FXML
    private Label welcomeText;




    public void onHelloButtonClick() {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;
        //con =null;
        int iduser= UserSession.instance.getID(); //int iduser=String.valueOf(UserSession.instance.getID());
        String Full = Fullname.getText();
        String Emaile = temail.getText();
        String password = paswd.getText();
        String newpassworde = cpaswd.getText();


        if(Full=="" || Emaile=="" || password=="" || newpassworde==""  ){
            System.out.println("error");

        }else{

            String Query1 ="select Password from javafx2.user where idUtilisateur ="+UserSession.instance.getID();
            try {
                preparedStatement = con.prepareStatement(Query1);
                preparedStatement.setInt(1, iduser);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.equals(password)) {

                    welcomeText.setText("oLD PASSWORD IS NOT TRUE");

                } else {

                    String Query = "UPDATE javafx2.user SET Full_Name=?,Email=?,Password=?,image=?,Gender=?,type=? WHERE idUtilisateur="+UserSession.instance.getID();
                    System.out.println(password);
                    System.out.println(Emaile);


                    try {
                        con= db.getConnection();
                        preparedStatement = con.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, Full);
                        preparedStatement.setString(2, Emaile);
                        preparedStatement.setString(3, newpassworde);
                        int Id;
                        Id = preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        welcomeText.setText("EREUR");
                    }
                }
            }catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    ////// EDITE PROFILS ENDHERE ///////////////////////////////

    //==========================================================Gridpane
    @FXML
    private  GridPane projectGrid;
    private List<Project> posts =  new ArrayList<>();

    private void LabelOfPrjError(Color color, String text) {
        LabelOfPrj.setTextFill(color);
        LabelOfPrj.setText(text);
        System.out.println(text);
    }

    //to open a new window of About_project
    private Stage stage;
    private Scene scene;
    private Parent root;

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


    //Search Declaration :

    @FXML
    private Label Search_Desc_label;

    @FXML
    private Label Search_ED_label;

    @FXML
    private Label Search_SD_label;

    @FXML
    private Label Search_Team_label;

    @FXML
    private Button Search_and_join_btn;

    @FXML
    private TextField Search_idprj;

    @FXML
    private TextField s2searchFields;

    @FXML
    private Label Search_title_label;

    @FXML
    private Button Serch_btn;

    @FXML
    private Button topbarbtn;

    int seachedteamid = -1;



    //end search declarion

    public void HandleSearchProject(ActionEvent event) throws SQLException {

        String theprojectid ="";
        //int groupid = Integer.getInteger(Search_idprj.getText());
        Object source = event.getSource();

        if(Serch_btn.equals(source)){
            theprojectid = Search_idprj.getText();
            Search_idprj.clear();
        }else if(topbarbtn.equals(source)){
            theprojectid = s2searchFields.getText();
            s2searchFields.clear();
            pathfiletxt.setText("/dashboard/project/Jointeam");
            settings.setVisible(false);
            addproject.setVisible(false);
            dashboard.setVisible(false);
            listeproject.setVisible(false);
            detailanchorpane.setVisible(false);
            SearchProject.setVisible(true);
            SearchProject.toFront();
        }


        System.out.println(theprojectid);
        int idprjass;
        idprjass = Integer.valueOf(theprojectid);
        String  Searchquery = "select * from project where idProjet = ?";
        con = db.getConnection();

        preparedStatement = con.prepareStatement(Searchquery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, idprjass);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Search_title_label.setText(resultSet.getString(2));
            Search_Desc_label.setText(resultSet.getString(3));
            Search_SD_label.setText(resultSet.getString(4));
            Search_ED_label.setText(resultSet.getString(5));
            Search_Team_label.setText(resultSet.getString(9));
            seachedteamid = resultSet.getInt(9);
        }

        Search_and_join_btn.setVisible(true);

        Search_and_join_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String addtoparticiped = "INSERT INTO participed VALUES(?,?,?,?,?)";
                con = db.getConnection();
                try {
                    preparedStatement = con.prepareStatement(addtoparticiped, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, "Developer");
                    preparedStatement.setString(2, "A");
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setInt(4, seachedteamid);
                    preparedStatement.setInt(5,UserSession.instance.getID());

                    preparedStatement.executeUpdate();
                    fillout();


                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });

    }





    public void HandlClick(ActionEvent event ){

        Object source = event.getSource();
        if (dashboardmenubtn.equals(source)) {
            pathfiletxt.setText("/dashboard");
            getMyprojects();
            getMytasks();
            getMyteams();
            getNewtaskes();
            getTodotaskes();
            getDoneTaskes();
            SearchProject.setVisible(false);
            addproject.setVisible(false);
            settings.setVisible(false);
            detailanchorpane.setVisible(false);
            listeproject.setVisible(false);
            dashboard.setVisible(true);
            dashboard.toFront();
            System.out.println("salam");
        } else if (projectlistebtn.equals(source)) {
            pathfiletxt.setText("/dashboard/My project");
            SearchProject.setVisible(false);
            addproject.setVisible(false);
            detailanchorpane.setVisible(false);
            settings.setVisible(false);
            dashboard.setVisible(false);
            listeproject.setVisible(true);
            listeproject.toFront();
            System.out.println("salam2");
        } else if (settingsmenubtn.equals(source)) {
            pathfiletxt.setText("/dashboard/user/Setting");
            SearchProject.setVisible(false);
            addproject.setVisible(false);
            dashboard.setVisible(false);
            detailanchorpane.setVisible(false);
            settings.setVisible(true);
            settings.toFront();
        }else if (teammenubtn.equals(source)) {
            pathfiletxt.setText("/dashboard/project/Jointeam");
            settings.setVisible(false);
            addproject.setVisible(false);
            dashboard.setVisible(false);
            listeproject.setVisible(false);
            detailanchorpane.setVisible(false);
            SearchProject.setVisible(true);
            SearchProject.toFront();
        } else if (addprj.equals(source)) {
            pathfiletxt.setText("/dashboard/project/AddProject");
            SearchProject.setVisible(false);
            dashboard.setVisible(false);
            listeproject.setVisible(false);
            detailanchorpane.setVisible(false);
            settings.setVisible(false);
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

    /////////////////////////////////////////////////////////
    //get my projects

    private void getMyprojects(){

        //String querey="select count (*) AS pr from project ";
        con = db.getConnection();
        try{
            preparedStatement = con.prepareStatement("select count(pr.idProjet) from project pr\n" +
                    "join participed p\n" +
                    "on p.Team_idTeam=pr.idTeam\n" +
                    "join user u\n" +
                    "on u.idUtilisateur=p.userid\n" +
                    "AND u.idUtilisateur="+UserSession.instance.getID());
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                int countpr=rs.getInt(1);
                l1.setText(String.valueOf(countpr));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    //get my teams


    private void getMyteams(){

        //String querey="select count (*) AS pr from project ";
        con = db.getConnection();
        try{
            preparedStatement = con.prepareStatement("select count(u.idUtilisateur) AS MYTEAMS\n" +
                    " from user u\n" +
                    "cross join participed p \n" +
                    "on u.idUtilisateur =p.userid\n" +
                    "AND u.idUtilisateur="+UserSession.instance.getID());
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                int countteams=rs.getInt(1);
                l3.setText(String.valueOf(countteams));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //get my tasks

    private void getMytasks(){

        String querey="select count(*) from tache where participerid = ?" ;
        con = db.getConnection();
        try{
            preparedStatement = con.prepareStatement(querey,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,UserSession.instance.getID());
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                int count =rs.getInt(1);
                nbtask_label1.setText(String.valueOf(count));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getNewtaskes(){
        String querey="select count(*) from tache where participerid = ? and Status = 'A'" ;
        con = db.getConnection();
        try{
            preparedStatement = con.prepareStatement(querey,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,UserSession.instance.getID());
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                int count =rs.getInt(1);
                nb_new_taskes.setText(String.valueOf(count));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getTodotaskes(){
        String querey="select count(*) from tache where participerid = ? and Status = 'P'" ;
        con = db.getConnection();
        try{
            preparedStatement = con.prepareStatement(querey,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,UserSession.instance.getID());
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                int count =rs.getInt(1);
                nb_todo_taskes.setText(String.valueOf(count));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getDoneTaskes(){
        String querey="select count(*) from tache where participerid = ? and Status = 'D'" ;
        con = db.getConnection();
        try{
            preparedStatement = con.prepareStatement(querey,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,UserSession.instance.getID());
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                int count =rs.getInt(1);
                nb_done_taskes.setText(String.valueOf(count));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sessiion configuration

        sessionName.setText(UserSession.instance.getUserName());
        sessionId.setText(String.valueOf(UserSession.instance.getID()));
        Timenow();
        datenow();
        getMyprojects();
        getMytasks();
        getMyteams();
        getNewtaskes();
        getTodotaskes();
        getDoneTaskes();
        System.out.println("Ara hna");

        prjlisten = new ProjectListener() {
            @Override
            public void PrjOnclickListener(Project project) {

                System.out.println(project.getId_project());

                pathfiletxt.setText("/dashboard/:idpoject="+ project.getId_project());

                Dtchefprj.setText(String.valueOf(project.getId_chef()));
                Dtprjtitle.setText(project.getTitle());
                Dtprjdesc.setText(project.getDescription());
                DtprjId.setText(String.valueOf(project.getId_project()));
                Dtrole.setText("Mazal");

                SearchProject.setVisible(false);
                addproject.setVisible(false);
                detailanchorpane.setVisible(true);
                listeproject.setVisible(false);
                dashboard.setVisible(false);
                detailanchorpane.toFront();

                indevBtnTaskes.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("Clicki 3lia Clicki 3lia");
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskestable.fxml"));
                        Parent root1 = null;
                        try {
                            root1 = (Parent) fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        TaskesGest controller2 = fxmlLoader.getController();
                        //TaskesGest.idproject = 12;
                        controller2.setIdproject(project.getId_project());
                        controller2.setTitleprj(project.getTitle());

                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.UNDECORATED);
                        //stage.setUserData(project);
                        stage.setTitle("Taskes");
                        stage.setScene(new Scene(root1));
                        stage.show();
                    }
                });

            }
        };

        fillout();

    }

    public  List<Project> data() throws ParseException {
        System.out.println("hani kangad fdata");
        con = db.getConnection();
        String participedQuery = "select * from project  join participed where project.idTeam = participed.Team_idTeam and participed.userid = ?";

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
                p.setDescription(resultSet.getString(3));
                sDate1= String.valueOf(resultSet.getDate(5));
                date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                p.setDate_fin(date1);
                p.setId_chef(resultSet.getInt(10));
                ls.add(p);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ls ;

    }
}
