package com.example.demo;

import com.example.demo.models.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class TaskesGest implements Initializable {
    PreparedStatement preparedStatement = null;
    PreparedStatement listepreparedStatement = null;
    ResultSet resultSet = null;
    Connection con = null;



    @FXML
    private TableView<Tache> tachetabel;
    @FXML
    private TableColumn<Tache, Integer> idcol;
    @FXML
    private TableColumn<Tache, String> titlecol;
    @FXML
    private TableColumn<Tache, String> desccol;
    @FXML
    private TableColumn<Tache, String> datedcol;
    @FXML
    private TableColumn<Tache, String> datefcol;
    @FXML
    private TableColumn<Tache, String> statuscol;
    @FXML
    private TableColumn ActionCol;

    ObservableList<Tache> Tachliste = FXCollections.observableArrayList();

    @FXML
    private AnchorPane taskespan;

    int idproject;
    String titleprj;

    @FXML
    Label idlabel;

    @FXML
    Label titlelabel;

    @FXML
    Label errorlabel;

    String query = "select * from javafx2.tache where participerid = ? and projetid = ?";
    @FXML
    private Button btnA;

    @FXML
    private Button btnD;

    @FXML
    private Button btnP;
    @FXML
    private Button btnAll;

    @FXML
    private  Button newtaskbtn;

    @FXML
    private  Button tabeleviewaff;

    @FXML
    private  Button close;

    @FXML
    private HBox asignetaskhbox;
    @FXML
    private VBox tablevbox;

    //tache FXML declaration

    ArrayList<String> namesofusers = new ArrayList<String>();
    ArrayList<Integer> idofusers = new ArrayList<Integer>();

    @FXML
    private ChoiceBox<String> spiinerusers;

    @FXML
    private DatePicker enddate;

    @FXML
    private DatePicker startdate;

    @FXML
    private Label tachesucsesslabel;

    @FXML
    private TextField tache_description;

    @FXML
    private TextField tache_title;

    @FXML
    private TextField tache_project_id;

    @FXML
    private  Button close_tache;

    public void closeapp(){
        // get a handle to the stage
        Stage stage = (Stage) close_tache.getScene().getWindow();
        // do what you have to do
        stage.close();
    }



    public void addnewtask() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);



        String t_title = tache_title.getText();
        String t_desc = tache_description.getText();
        String t_s_d = (startdate.getValue()).format(formatter);
        String t_e_d = (enddate.getValue()).format(formatter);
        String user_t = spiinerusers.getValue();

        int index = namesofusers.indexOf(spiinerusers.getValue());

        System.out.println("///////////////////////////////////////////////");
        System.out.println(" " + t_title + " " + t_desc  + " "+ t_s_d  + " "  + t_e_d  + " " + user_t + " "  + " " + idofusers.get(index)  + " ");
        System.out.println("///////////////////////////////////////////////");


        String addtaskquery = "INSERT INTO tache VALUES(NULL , ? , ? , ? , ? ,?, ? , ?)";

        con = db.getConnection();

        preparedStatement = con.prepareStatement(addtaskquery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, t_title);
        preparedStatement.setString(2, t_desc);
        preparedStatement.setString(3, t_s_d);
        preparedStatement.setString(4, t_e_d);
        preparedStatement.setString(5, "A");
        preparedStatement.setInt(6, idofusers.get(index));
        preparedStatement.setInt(7, idproject);
        preparedStatement.execute();
        tachesucsesslabel.setTextFill(Color.GREEN);
        tachesucsesslabel.setText("Add Successful ........... ");

    }


    public void handlequery(ActionEvent event) throws SQLException {
        Object source = event.getSource();
        String cara = "none";
        if(btnA.equals(source)){
            cara ="A";

        }else if(btnP.equals(source)){
            cara ="P";

        }else{
            cara = "D";
        }

        this.query = "select * from javafx2.tache where participerid = ? and projetid = ? and Status = ? ";



        if(btnAll.equals(source)){
            this.query = "select * from javafx2.tache where participerid = ? and projetid = ?";
            listepreparedStatement = null;
        }else{
            listepreparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            listepreparedStatement.setInt(1, UserSession.instance.getID());
            listepreparedStatement.setInt(2, idproject);
            listepreparedStatement.setString(3, cara);
        }

        refreshTable();
    }


    public  void setIdproject(int id ){
        idproject = id;
        idlabel.setText("Project ID : " + String.valueOf(idproject));
    }

    public  void setTitleprj(String title){
        titleprj = title;
        titlelabel.setText("Project Title : " + titleprj);
    }


    public void tachhandle(int idtach , String etat ) throws SQLException {
        String queryupd = "update tache set status = ?  where idTache = ?";
        con = db.getConnection();
        preparedStatement = con.prepareStatement(queryupd, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, etat);
        preparedStatement.setInt(2, idtach);
        preparedStatement.execute();

        refreshTable();


    }

    private void refreshTable() {
        con = db.getConnection();
        Tachliste.clear();
        tachetabel.refresh();

        try {
            if(listepreparedStatement == null){
                preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, UserSession.instance.getID());
                preparedStatement.setInt(2, idproject);
                resultSet = preparedStatement.executeQuery();
            }else {
                resultSet = listepreparedStatement.executeQuery();
            }

            //System.out.println(UserSession.instance.getID());

            while (resultSet.next()) {
                Tachliste.add(new Tache(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)));
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println(resultSet.getString(6));

                System.out.println("----------------------------------");

                tachetabel.setItems(Tachliste);
            }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private class ButtonCell extends TableCell<Tache, Boolean> {
        Image imgEdit = new Image(getClass().getResourceAsStream("/com/example/demo/Assets/doing.png"), 70, 20, false, false);
        final Button cellButton1 = new Button();
        Image imgDeete = new Image(getClass().getResourceAsStream("/com/example/demo/Assets/done.png"), 70, 20, false, false);
        final Button deleteButton = new Button();

        ButtonCell(final TableView tblView){
            cellButton1.setStyle("-fx-background-color: #3cb371; -fx-border-radius: #00CC00;");
            cellButton1.setTextFill(Color.WHITE);
            cellButton1.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String title = tachetabel.getItems().get(x).getTitle();
                    String  desc = tachetabel.getItems().get(x).getDescription();
                    System.out.println(title);
                    try {
                        tachhandle(tachetabel.getItems().get(x).getId_tache(),"P");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            });



            deleteButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    try {
                        tachhandle(tachetabel.getItems().get(x).getId_tache(),"D");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

            }});
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            System.out.println(empty);
            if(!empty){

                deleteButton.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                ImageView iv = new ImageView();
                iv.setImage(imgDeete);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                deleteButton.setGraphic(iv);
                setGraphic(deleteButton);
                setAlignment(Pos.CENTER);
                setText(null);
                //cellButton1
                cellButton1.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                ImageView v = new ImageView();
                v.setImage(imgEdit);
                v.setPreserveRatio(true);
                v.setSmooth(true);
                v.setCache(true);
                cellButton1.setGraphic(v);
                setGraphic(cellButton1);
                setAlignment(Pos.CENTER);
                setText(null);

                HBox pane = new HBox(cellButton1, deleteButton);
                pane.setStyle("-fx-alignment:center");
                pane.setSpacing(10);
                setGraphic(pane);
            }

        }
    }

    public void  handlchangeview(ActionEvent event) throws SQLException {
        String checkquery = "select idchef from project where idProjet = ?";
        int thechef = -1;
        con = db.getConnection();


        Object source = event.getSource();

        if(newtaskbtn.equals(source)){
            tache_project_id.setText(String.valueOf(idproject));

            try {
                preparedStatement = con.prepareStatement(checkquery, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, idproject);
                System.out.println("ha project b3da " + idproject);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    thechef = resultSet.getInt(1);
                    System.out.println("ha chef " + thechef);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(thechef == UserSession.instance.getID()){
                asignuserstochoicebox();
                asignetaskhbox.setVisible(true);
                tablevbox.setVisible(false);
            }else{
                errorlabel.setText("Only for Project chef");
            }



        }else if(tabeleviewaff.equals(source)){
            tablevbox.setVisible(true);
            asignetaskhbox.setVisible(false);
        }else if(close.equals(source)){
            System.out.println("baghi tkhreej hh");
        }

    }

    public void asignuserstochoicebox() throws SQLException {
        namesofusers.clear();
        idofusers.clear();
        spiinerusers.getItems().clear();
        con = db.getConnection();
        String usersquery = "select Full_Name , idUtilisateur from user u join (select userid " +
                "from project join participed " +
                "                        where project.idTeam = participed.Team_idTeam " +
                "                        and project.idProjet = ?) j " +
                "where u.idUtilisateur = j.userid";

        preparedStatement = con.prepareStatement(usersquery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, idproject);
        resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                namesofusers.add(resultSet.getString(1));
                idofusers.add(resultSet.getInt(2));
                System.out.println(resultSet.getString(1));
            }



        spiinerusers.getItems().addAll(namesofusers);
    }


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        refreshTable();

            idcol.setCellValueFactory(new PropertyValueFactory<Tache,Integer>("id_tache"));
            titlecol.setCellValueFactory(new PropertyValueFactory<Tache,String>("Title"));
            desccol.setCellValueFactory(new PropertyValueFactory<Tache,String>("description"));
            datedcol.setCellValueFactory(new PropertyValueFactory<Tache,String>("Date_debut"));
            datefcol.setCellValueFactory(new PropertyValueFactory<Tache,String>("date_fin"));
            statuscol.setCellValueFactory(new PropertyValueFactory<Tache,String>("etat"));

            ActionCol.setSortable(false);

            ActionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tache, Boolean>, ObservableValue<Boolean>>(){
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Tache, Boolean> p) {
                    return new SimpleBooleanProperty(p.getValue() != null);
                }

            });

            ActionCol.setCellFactory(new Callback<TableColumn<Tache, Boolean>, TableCell<Tache, Boolean>>(){
                @Override
                public TableCell<Tache, Boolean> call(TableColumn<Tache, Boolean> p) {
                    return new ButtonCell(tachetabel);
                }
            });

    }
}
