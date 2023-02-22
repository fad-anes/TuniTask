package gui;
import entite.offre;
import entite.commentaire;
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
import service.commentaireservice;
public class VoirCommentaire implements Initializable{
    @FXML
    private TableView table;
    @FXML
    private TableColumn idc;
    @FXML
    private TableColumn ido;
    @FXML
    private TableColumn cmm;
    @FXML
    private TableColumn tit;
    @FXML
    private Button rt;
    @FXML
    private Button aj;
    @FXML
    private Button Modifier;
    @FXML
    private Button supp;
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
        List<commentaire> list=new ArrayList<>();
        commentaireservice ps=new commentaireservice();
        list=ps.readall();
        ObservableList<commentaire> obs= FXCollections.observableArrayList(list);
        idc.setCellValueFactory(new PropertyValueFactory<>("idcommentaire"));
        ido.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        cmm.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        tit.setCellValueFactory(new PropertyValueFactory<>("titre"));
        table.setItems(obs);
        table.refresh();
    }
    @FXML
    private void rt(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("afficheroffre.fxml"));
        Parent root=loader.load();

        Afficheroffre dc=loader.getController();
        rt.getScene().setRoot(root);
    }
    @FXML
    private void versajcm(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ajoutcomm.fxml"));
        Parent root=loader.load();

        Ajoutcomm dc=loader.getController();
        rt.getScene().setRoot(root);
    }
    @FXML
    private void versmdcm(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("modcmm.fxml"));
        Parent root=loader.load();

        Modcmm dc=loader.getController();
        rt.getScene().setRoot(root);
    }
    @FXML
    private void supp(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("supprimercmm.fxml"));
        Parent root=loader.load();

        Supprimercmm dc=loader.getController();
        rt.getScene().setRoot(root);
    }
}
