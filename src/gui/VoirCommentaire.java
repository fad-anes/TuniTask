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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.offreservice;
import service.commentaireservice;
public class VoirCommentaire implements Initializable{

    @FXML
    private Button rt;
    @FXML
    private Button ref;
    @FXML
    private HBox hb;
    @FXML
    private VBox vbox;
    @FXML
    private Button ajout;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    private  int i;
    Parent p=new HBox();
    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO


    }
    @FXML
    private void rt(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("afficheroffre.fxml"));
        Parent root=loader.load();

        Afficheroffre dc=loader.getController();

        ajout.getScene().setRoot(root);
    }
    @FXML
    private void ref(ActionEvent event) throws IOException {
        hb.getChildren().clear();
        vbox.getChildren().clear();
        try {
            offre o=new offre();
            offreservice ps=new offreservice();

            o=ps.ReadById(i);
            FXMLLoader fxl=new FXMLLoader();
            fxl.setLocation(getClass().getResource("cardoffremessage.fxml"));
            Parent root=fxl.load();
            p=root;
            Cardoffremessage c=fxl.getController();
            c.setdata(o);
            hb.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<commentaire> list=new ArrayList<>();
            commentaireservice ps=new commentaireservice();
            list=ps.ReadById(i);
            for(int j=0;j<list.size();j++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("cardcommentaire.fxml"));
                Parent root=fxl.load();
                Cardcommentaire c=fxl.getController();
                c.setdata(list.get(j));
                vbox.getChildren().add(root);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void versajout(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ajoutcomm.fxml"));
        Parent root=loader.load();

        Ajoutcomm dc=loader.getController();
        dc.setId(i);
        ajout.getScene().setRoot(root);
    }
    @FXML
    private void versupp(ActionEvent event) throws IOException {
        vbox.getChildren().clear();
        try {
            List<commentaire> list=new ArrayList<>();
            commentaireservice ps=new commentaireservice();
            list=ps.ReadById(i);
            for(int j=0;j<list.size();j++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("supprimercmm.fxml"));
                Parent root=fxl.load();
                Supprimercmm  c=fxl.getController();
                c.setdata(list.get(j));

                vbox.getChildren().add(root);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void vermod(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("modcmm.fxml"));
        Parent root=loader.load();
        Modcmm dc=loader.getController();
        dc.setId(i);
        ajout.getScene().setRoot(root);
    }
}
