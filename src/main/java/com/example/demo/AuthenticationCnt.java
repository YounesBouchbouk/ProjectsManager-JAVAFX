package com.example.demo;
import com.example.demo.models.db;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import  java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;

public class AuthenticationCnt implements Initializable {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection con = null;
    Stage dialogStage = new Stage();
    Scene scene;



    @FXML
    private HBox boxclose;
    @FXML
    private AnchorPane root;

    @FXML
    private Pane vb_content;



    @FXML
    private TextField Emailuser;

    @FXML
    private PasswordField Password;


    @FXML
    private Label signioerror;

    //SignuP Ids

    @FXML
    private Label Signuperror;

    @FXML
    private PasswordField upassword;

    @FXML
    private PasswordField aconfirm;

    @FXML
    private DatePicker date;

    @FXML
    private TextField emailsignup;

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;



    @FXML
    private TextField username;
    @FXML
    private ChoiceBox<String> Speciality;
    private String[] specialities = {"Developer" , "Designer"};

    @FXML
    private ChoiceBox<String> gendercheckbox;
    private String[] Gender = {"M" , "F"};








    @FXML
    void leftdrop(MouseEvent event) {
        TranslateTransition translatehbox = new TranslateTransition(Duration.seconds(3) , boxclose );
        TranslateTransition translat = new TranslateTransition(Duration.seconds(2) , vb_content);
        translatehbox.setToX(0);
        translat.setToX(root.getLayoutX());
        boxclose.setAlignment(Pos.CENTER_LEFT);

        translatehbox.play();
        translat.play();
    }

    @FXML
    void rightdrop(MouseEvent event) {
        TranslateTransition translatehbox = new TranslateTransition(Duration.seconds(3) , boxclose );
        TranslateTransition translat = new TranslateTransition(Duration.seconds(2) , vb_content);
        translat.setToX(vb_content.getLayoutX() + (root.getPrefWidth()  - vb_content.getPrefWidth()));
        translatehbox.setToX(vb_content.getPrefWidth() - boxclose.getPrefWidth());
        boxclose.setAlignment(Pos.CENTER_RIGHT);

        translatehbox.play();
        translat.play();
    }
    @FXML
    private void CloseApp(MouseEvent event){
        System.out.println("salam");
        System.exit(0);
    }
    private  void openurl(String url) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(url));
    }
    public void instagramme(MouseEvent event) throws URISyntaxException, IOException {
        this.openurl("https://www.instagram.com");
    }
    public void facebook(MouseEvent event) throws URISyntaxException, IOException {
        this.openurl("https://www.facebook.com");
    }
    public void twitter(MouseEvent event) throws URISyntaxException, IOException {
        this.openurl("https://www.twitter.com");
    }

    private void setLblError(Color color, String text) {
        signioerror.setTextFill(color);
        signioerror.setText(text);
        System.out.println(text);
    }


    @FXML
    public void loginauthentication(ActionEvent event) {

        //this.signioerror.setText(this.Emailuser.getText());
        //System.out.println("ssssss");
        String email = this.Emailuser.getText();
        String password = this.Password.getText();
        con = db.getConnection();


        String Query ="select * from javafx2.user where  Email = ? and Password = ?";
        System.out.println(Query);


        try {

            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {

                setLblError(Color.RED, "Enter Correct Email/Password");


            } else {
                setLblError(Color.GREEN, "Login Successful..Redirecting..");


                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Dashboardview.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();

            }

        } catch (SQLException  | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void signup(ActionEvent event){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

        String FirstName = firstname.getText();
        String LastName = lastname.getText();
        String Email = emailsignup.getText();
        String DateNaiss = (date.getValue()).format(formatter);
        String password = upassword.getText();
        String confirmpassword = aconfirm.getText();




        if(FirstName=="" || LastName=="" || Email=="" || DateNaiss=="" || password=="" || confirmpassword=="" ){
            Signuperror.setText("All fields Are required");
        }else if(!password.equals(confirmpassword)){
            Signuperror.setText("PAsswords Not match");
        }else{
            con = db.getConnection();
            String Query ="insert into javafx2.user values(NULL , ?  , ? , ? , ?, ? , ? , ? , 0 )";
            System.out.println(gendercheckbox.getValue());
            System.out.println(password);
            System.out.println(Email);
            System.out.println(DateNaiss);


            try {

                preparedStatement = con.prepareStatement(Query);
                preparedStatement.setString(1, FirstName);
                preparedStatement.setString(2, Email);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, "./img/test/ui.jpg");
                preparedStatement.setString(5, gendercheckbox.getValue());
                preparedStatement.setString(6, DateNaiss);
                preparedStatement.setString(7, Speciality.getValue());
                preparedStatement.executeUpdate();

                setLblError(Color.GREEN, "SignUp Successful..Please Login..");

                TranslateTransition translatehbox = new TranslateTransition(Duration.seconds(3) , boxclose );
                TranslateTransition translat = new TranslateTransition(Duration.seconds(2) , vb_content);
                translatehbox.setToX(0);
                translat.setToX(root.getLayoutX());
                boxclose.setAlignment(Pos.CENTER_LEFT);

                translatehbox.play();
                translat.play();


            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Signuperror.setText("Password machi bhale bhale");
        }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Speciality.getItems().addAll(specialities);
        gendercheckbox.getItems().addAll(Gender);
    }
}
