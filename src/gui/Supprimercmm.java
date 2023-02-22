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
public class Supprimercmm implements Initializable{
    @FXML
    private Label idcc;
    @FXML
    private Label supss;
    @FXML
    private TextField idcm;
    @FXML
    private Button retour;
    @FXML
    private Button supp;
    @Override
    public void initialize(URL url, ResourceBundle rb)  {}
    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("voir commentaire.fxml"));
        Parent root=loader.load();

        VoirCommentaire dc=loader.getController();
        idcm.getScene().setRoot(root);
    }
    @FXML
    private void supp(ActionEvent event) throws IOException {
        commentaireservice ps=new commentaireservice();
        int t=0;
        if(idcm.getText().isEmpty()){ t=1;
            this.idcc.setText("champ manquant");}
        else if(!ps.Findidcm(Integer.parseInt(idcm.getText()))){
            t=1;
            this.idcc.setText("commentaire introuvable");}
        else this.idcc.setText("");
        if(t==0){

            ps.delete(Integer.parseInt(idcm.getText()));
            this.supss.setText("suppression avec succès!");}
    }
}
