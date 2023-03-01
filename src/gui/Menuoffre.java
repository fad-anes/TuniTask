package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import service.offreservice;
public class Menuoffre implements Initializable {
    @FXML
    private Button ajm;
    @FXML
    private Button affichm;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO

    }
    @FXML
    private void versinaj(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ajouteroffre.fxml"));
        Parent root=loader.load();

        Ajouteroffre dc=loader.getController();
        ajm.getScene().setRoot(root);
    }
    @FXML
    private void versaff(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("afficheroffre.fxml"));
        Parent root=loader.load();

        Afficheroffre dc=loader.getController();
        ajm.getScene().setRoot(root);
    }
    @FXML
    private void vermodif(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("rechmodifoffre.fxml"));
        Parent root=loader.load();

        Rechmodifoffre dc=loader.getController();
        ajm.getScene().setRoot(root);
    }
    @FXML
    private void verssup(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("supprimeroffre.fxml"));
        Parent root=loader.load();

        Supprimeroffre dc=loader.getController();
        ajm.getScene().setRoot(root);
    }
}
