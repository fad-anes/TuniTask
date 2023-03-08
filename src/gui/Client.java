package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import gui.UserSession;
import entite.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ServiceUsers;

import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Client {
    @FXML
    private ImageView imageuser;
    @FXML
    private Pane PaneValide;
    @FXML
    private ImageView image1;
    @FXML
    private Label Acceuil;
    @FXML
    private Label demande;
    @FXML
    private Label reclamation;
    @FXML
    private Label offre;

    int id= UserSession.getInstance().getId().intValue();
    @FXML
    private void User(MouseEvent event) {

    }

    @FXML
    private void Demande(MouseEvent event) {
        ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {


            root = FXMLLoader.load(getClass().getResource("InterfaceClientDemande.fxml"));
            Afficheroffre dc=new Afficheroffre();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TuniTask");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TuniTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void reclamation(MouseEvent event) {
    }

    @FXML
    private void offre(MouseEvent event) {
        ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {


            root = FXMLLoader.load(getClass().getResource("afficheroffre.fxml"));
            Afficheroffre dc=new Afficheroffre();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TuniTask");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TuniTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void log_out(ActionEvent event) {
        ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TuniTask");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TuniTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Profile(ActionEvent event) {
        ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Compte.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TuniTask");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TuniTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}