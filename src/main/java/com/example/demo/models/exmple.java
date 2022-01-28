/* package com.example.demo.models;

public class exmple {
    ObservableList  liste = FXCollections.observableArrayList();
    private void affichersalles() throws SQLException{
        liste.clear();
        connecter c=new connecter();
        ResultSet rs=c.salleinfs();
        while(rs.next()){
            liste.add(new salle(rs.getString(2),rs.getString(4),rs.getString(3),rs.getString(6),rs.getString(5)));
            tablesalle.setItems(liste);
        }
    }
    private void chargersalles() throws SQLException{
        connecter c=new connecter();

        ResultSet rs=c.salleinfs();
        affichersalles();

        ns.setCellValueFactory(new PropertyValueFactory<>("nom"));
        vs.setCellValueFactory(new PropertyValueFactory<>("ville"));
        adrs.setCellValueFactory(new PropertyValueFactory<>("adrs"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tels"));
        emai.setCellValueFactory(new PropertyValueFactory<>("email"));
        edt.setSortable(false);
        //,;
        edt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<salle, Boolean>, ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<salle, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }

        });

        edt.setCellFactory(new Callback<TableColumn<salle, Boolean>, TableCell<salle, Boolean>>(){
            @Override
            public TableCell<salle, Boolean> call(TableColumn<salle, Boolean> p) {
                return new ButtonCell(tablesalle);
            }
        });
        //tableusers.getColumns().add(act);

    }

    //Define the button cell
    private class ButtonCell extends TableCell<salle, Boolean> {
        Image imgEdit = new Image(getClass().getResourceAsStream("/image/edit.png"), 20, 20, false, false);
        final Button cellButton1 = new Button();
        Image imgDeete = new Image(getClass().getResourceAsStream("/image/remove.png"), 20, 20, false, false);
        final Button deleteButton = new Button();



        ButtonCell(final TableView tblView){
            cellButton1.setStyle("-fx-background-color: #3cb371; -fx-border-radius: #00CC00;");
            cellButton1.setTextFill(Color.WHITE);
            cellButton1.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String nom = tablesalle.getItems().get(x).getNom();
                    String  v = tablesalle.getItems().get(x).getVille();
                    String  emal = tablesalle.getItems().get(x).getEmail();
                    String   a = tablesalle.getItems().get(x).getAdrs();
                    String ccc=tablesalle.getItems().get(x).getTels();
                    m=emal;n=nom;vi=v;ad=a; telephone=ccc;
                    try {
                        editsalle() ;
                    } catch (IOException ex) {
                        Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(nom);

                }

            });

            //  deleteButton.setStyle("-fx-background-color: #f5053d; -fx-border-radius: #cc0000;");
            //deleteButton.setTextFill(Color.WHITE);

            deleteButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String n = tablesalle.getItems().get(x).getNom();
                    connecter c=new connecter();

                    int id = c.recup(n);
                    if(c.recup(n)>=0){id=c.recup(n);}else {System.out.println("ddgdd");}

                    Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);

                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Voulez vous vraiment supprimer cette salle");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    if (answer.get() == ButtonType.OK) {
                        if( c.delsalle("Delete FROM salle where id_s="+ id +"")){  try {
                            chargersalles();

                        } catch (SQLException ex) {
                            Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }


                    }}
            });}
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
}
*/