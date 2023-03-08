package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Freelancer {
    public void Acceuil(MouseEvent mouseEvent) {
    }

    public void Proposition(MouseEvent mouseEvent) {
        Parent root;
        try {
            ( (Node) mouseEvent.getSource()).getScene().getWindow().hide();
            root = FXMLLoader.load(getClass().getResource("PropositionList.fxml"));

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

    public void log_out(ActionEvent event) {
    }

    public void Profile(ActionEvent event) {
    }

    public void offre(MouseEvent mouseEvent) {
        // ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("menuoffre.fxml"));
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

    public void evénement(MouseEvent mouseEvent) {
    }

    public void Demande(MouseEvent mouseEvent) {
        Parent root;
        try {
            ( (Node) mouseEvent.getSource()).getScene().getWindow().hide();
            root = FXMLLoader.load(getClass().getResource("ListeDemande.fxml"));

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

    public void reclamation(MouseEvent mouseEvent) {
    }

    public void quizfreelancer(MouseEvent mouseEvent) {
        Parent root;
        try {
         //   ( (Node) mouseEvent.getSource()).getScene().getWindow().hide();
            root = FXMLLoader.load(getClass().getResource("displayquizsfreelancer.fxml"));

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
