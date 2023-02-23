/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entite.Demande;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import modele.Demande;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class AjouterDemandeController implements Initializable {

    @FXML
    private TextField clientIdField;
    @FXML
    private TextField titreField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField salaireField;
    @FXML
    private TextField delaiField;
    @FXML
    private TextField langageField;
    @FXML
    private Button ajouterButton;
     @FXML
    private Button consulterTableButton;
    
    private ServiceDemande ServiceDemande;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceDemande = new ServiceDemande();
    }    

    @FXML
    private void ajouterDemande(ActionEvent event) {
        int clientId = Integer.parseInt(clientIdField.getText());
        String titre = titreField.getText();
        String description = descriptionField.getText();
        String salaire = salaireField.getText();
        String delai = delaiField.getText();
        String langage = langageField.getText();
        LocalDateTime now = LocalDateTime.now();
        Timestamp ls = Timestamp.valueOf(now);
        if(clientIdField.getText().isEmpty() || titre.isEmpty() || description.isEmpty() || salaire.isEmpty() || delai.isEmpty() || langage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            Demande demande = new Demande();
            demande.setId_client(clientId);
            demande.setTitre(titre);
            demande.setDescription(description);
            demande.setSalaire(salaire);
            demande.setDelai(delai);
            demande.setLangage(langage);
            demande.setCreated_at(ls);
            
            ServiceDemande.insert(demande);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("La demande a été ajoutée avec succès.");
            alert.showAndWait();
            
            clientIdField.clear();
            titreField.clear();
            descriptionField.clear();
            salaireField.clear();
            delaiField.clear();
            langageField.clear();
        }
    }

public void showInterfaceDemande(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InterfaceDemande.fxml"));
    Parent root = loader.load();
    InterfaceDemandeController controller = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
   
    @FXML
    private void consulterTable(ActionEvent event) {
        Stage currentStage = (Stage) consulterTableButton.getScene().getWindow();
        currentStage.close();
        
        // Ouvrir l'interface InterfaceDemande.fxml
        InterfaceDemandeController controller = new InterfaceDemandeController();
        controller.showInterfaceDemande();
    }
    }
    

