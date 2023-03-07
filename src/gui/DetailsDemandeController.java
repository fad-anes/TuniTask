/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Demande;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class DetailsDemandeController implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label titreLabel;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Label salaireLabel;
    @FXML
    private Label delaiLabel;
    @FXML
    private Label langageLabel;
    @FXML
    private Button retourButton;
    private Demande demande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage currentStage = (Stage) retourButton.getScene().getWindow();
        currentStage.close();
    }
      @FXML
    private void consulterTable(ActionEvent event) {
        Stage currentStage = (Stage) retourButton.getScene().getWindow();
        currentStage.close();
        
        // Ouvrir l'interface InterfaceDemande.fxml
        ListeDemandeController controller = new ListeDemandeController();
   //     controller.retour();
    }
    //@FXML
    //private VBox root
    
    public void setDemande(Demande demande) {
        this.demande = demande;
        nomLabel.setText(demande.getNom());
        prenomLabel.setText(demande.getPrenom());
        emailLabel.setText(demande.getEmail());
        titreLabel.setText(demande.getTitre());
        descriptionArea.setText(demande.getDescription());
        salaireLabel.setText(demande.getSalaire());
        delaiLabel.setText(demande.getDelai());
        langageLabel.setText(demande.getLangage());
    }
    
}