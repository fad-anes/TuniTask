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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceProposition;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class AjouterPropositionController implements Initializable {

    @FXML
    private TextField demandeIdField;
    @FXML
    private TextField freelancerIdField;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button consulterTableButton;
private ServiceProposition ServiceProposition;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServiceProposition = new ServiceProposition();
    }    

    @FXML
    private void ajouterProposition(ActionEvent event) {
        //int demandeId = Integer.parseInt(demandeIdField.getText());
         //int freelancerId = Integer.parseInt(freelancerIdField.getText());
        //if(demandeIdField.getText().isEmpty() || freelancerIdField.getText().isEmpty()) {
           // Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setTitle("Erreur");
            //alert.setHeaderText(null);
            //alert.setContentText("Veuillez remplir tous les champs.");
            //alert.showAndWait();
       // } else {
           // Proposition proposition = new Proposition();
           // proposition.setId_demande(demandeId);
           // proposition.setId_freelancer(freelancerId);
            
            //ServiceProposition.insert(proposition);
            
            //Alert alert = new Alert(Alert.AlertType.INFORMATION);
           // alert.setTitle("Succès");
            //alert.setHeaderText(null);
            //alert.setContentText("La proposition a été ajoutée avec succès.");
            //alert.showAndWait();
            
           // demandeIdField.clear();
           // freelancerIdField.clear();
           
        //}
        
         // Récupérer l'ID de la demande et l'ID du freelancer à partir des champs texte de la vue
    int demandeId = Integer.parseInt(demandeIdField.getText());
    int freelancerId = Integer.parseInt(freelancerIdField.getText());

    // Vérifier si les champs ne sont pas vides
    if(demandeIdField.getText().isEmpty() || freelancerIdField.getText().isEmpty()) {
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
        } else {
            // Créer un objet Proposition avec l'objet Demande et l'ID du freelancer
            Proposition proposition = new Proposition();
            proposition.setId_demande(demande);
            proposition.setId_freelancer(freelancerId);

            // Insérer l'objet Proposition dans la base de données en utilisant le service ServiceProposition
            ServiceProposition.insert(proposition);

            // Afficher une boîte de dialogue d'information pour informer l'utilisateur que la proposition a été ajoutée avec succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("La proposition a été ajoutée avec succès.");
            alert.showAndWait();

            // Effacer les champs texte de la vue pour que l'utilisateur puisse ajouter une nouvelle proposition
            demandeIdField.clear();
            freelancerIdField.clear();
        }
    }
    }
 @FXML
    private void showInterfaceProposition(ActionEvent event) throws IOException {
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
        Stage currentStage = (Stage) consulterTableButton.getScene().getWindow();
        currentStage.close();
        
        // Ouvrir l'interface InterfaceDemande.fxml
        InterfacePropositionController controller = new InterfacePropositionController();
        controller.showInterfaceProposition();
    }
}
