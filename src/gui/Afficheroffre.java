package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.offreservice;
public class Afficheroffre implements Initializable{
    @FXML
    private TableView table;
    @FXML
    private Button retourm;
    @FXML
    private TableColumn ido;
    @FXML
    private TableColumn fid;
    @FXML
    private TableColumn d;
    @FXML
    private TableColumn t;
    @FXML
    private TableColumn sa;
    @FXML
    private TableColumn skil;
    @FXML
    private TableColumn co;
    @FXML
    private TableColumn role;
    @FXML
    private TableColumn lang;
    @FXML
    private TableColumn nom;
    @FXML
    private TableColumn prenom;
    @FXML
    private TableColumn year;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<offre> list=new ArrayList<>();
        offreservice ps=new offreservice();
        list=ps.readall();
        ObservableList<offre> obs= FXCollections.observableArrayList(list);

        ido.setCellValueFactory(new PropertyValueFactory<>("idoffre"));
        fid.setCellValueFactory(new PropertyValueFactory<>("freelancer_id"));
        d.setCellValueFactory(new PropertyValueFactory<>("description"));
        t.setCellValueFactory(new PropertyValueFactory<>("titre"));
        sa.setCellValueFactory(new PropertyValueFactory<>("salaireH"));
        skil.setCellValueFactory(new PropertyValueFactory<>("skills"));
        co.setCellValueFactory(new PropertyValueFactory<>("country"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        lang.setCellValueFactory(new PropertyValueFactory<>("language"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        year.setCellValueFactory(new PropertyValueFactory<>("years"));

        table.setItems(obs);
        table.refresh();
    }
    @FXML
    private void retourm(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("menuoffre.fxml"));
        Parent root=loader.load();

        Menuoffre dc=loader.getController();
        table.getScene().setRoot(root);}
}
