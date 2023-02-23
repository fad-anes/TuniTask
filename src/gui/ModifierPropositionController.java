/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entite.Demande;
import entite.Proposition;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceDemande;
import service.ServiceProposition;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class ModifierPropositionController implements Initializable {

    @FXML
    private TextField demandeIdField;
    @FXML
    private TextField freelancerIdField;
    @FXML
    private Button modifierButton;
    @FXML
    private Button retourButton;

    /**
     * Initializes the controller class.
     */
    
    private ServiceProposition ServiceProposition;
    
    // Ajouter un champ pour l'ID
    private int idInt;

    public void setProposition(Proposition proposition, int idInt) {
        this.idInt = idInt;
        demandeIdField.setText(String.valueOf(proposition.getId_demande().getId()));
        freelancerIdField.setText(String.valueOf(proposition.getId_freelancer()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceProposition = new ServiceProposition();
    }    

    @FXML
    private void modifierProposition(ActionEvent event) {
        int demandeId= Integer.parseInt(demandeIdField.getText());
        int freelancerId = Integer.parseInt(freelancerIdField.getText());
  
        if (demandeIdField.getText().isEmpty() || freelancerIdField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            // Créer une instance de la classe ServiceDemande
        ServiceDemande serviceDemande = new ServiceDemande();

        // Récupérer l'objet Demande correspondant à l'ID de la demande
        Demande demande = serviceDemande.readById(demandeId);

            if (demande == null) {
            // Si l'objet Demande n'existe pas, afficher une boîte de dialogue d'erreur et ne pas ajouter la proposition
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La demande correspondante n'existe pas.");
            alert.showAndWait();
            }else {
                // Créer un objet Proposition avec l'objet Demande et l'ID du freelancer
            
            Proposition proposition = new Proposition();
            proposition.setId_demande(demande);
            proposition.setId_freelancer(freelancerId);
            

            ServiceProposition.update(proposition, idInt);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("La proposition a été modifiée avec succès.");
        alert.showAndWait();
        
            demandeIdField.clear();
            freelancerIdField.clear();
            
    }

    }
    }
    @FXML
    private void retourAuTable(ActionEvent event) throws IOException {
  FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InterfaceProposition.fxml"));
    Parent root = loader.load();
    InterfacePropositionController controller = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    @FXML
    private void consulterTable(ActionEvent event) {
        Stage currentStage = (Stage) retourButton.getScene().getWindow();
        currentStage.close();
        
        // Ouvrir l'interface InterfaceDemande.fxml
        InterfacePropositionController controller = new InterfacePropositionController();
        controller.retourAuTable();
    }
    
}
