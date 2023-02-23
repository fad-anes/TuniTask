/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entite.Demande;
import entite.Proposition;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ServiceProposition;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class InterfacePropositionController implements Initializable {

    @FXML
    private TableView<Proposition> tableProposition;
    @FXML
    private TableColumn<Proposition, Integer> idColumn;
    @FXML
    private TableColumn<Proposition, Demande> demandeIdColumn;
    @FXML
    private TableColumn<Proposition, Integer> freelancerIdColumn;
    @FXML
    private Button afficherButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button ajouterButton;
private ObservableList<Proposition> propositionsList;
    private ServiceProposition ServiceProposition;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        propositionsList = FXCollections.observableArrayList();
        ServiceProposition = new ServiceProposition();
        
        // Configure table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        demandeIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_demande"));
        freelancerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_freelancer"));
        
        // Populate table with data from database
        propositionsList.addAll(ServiceProposition.readAll());
        tableProposition.setItems(propositionsList);
    }    

    @FXML
    private void afficherPropositions(ActionEvent event) {
        // Refresh table with data from database
        propositionsList.clear();
        propositionsList.addAll(ServiceProposition.readAll());
        tableProposition.setItems(propositionsList);
    }
    
    public void populateTableProposition() {
    ObservableList<Proposition> propositions = FXCollections.observableArrayList();
    ServiceProposition serviceProposition = new ServiceProposition();
    List<Proposition> propositionList = serviceProposition.readAll();
    propositions.addAll(propositionList);
    tableProposition.setItems(propositions);
}

    @FXML
    private void supprimerProposition(ActionEvent event) throws IOException {
        // Charger le fichier FXML de la fenêtre de dialogue
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerProposition.fxml"));
    Parent root = loader.load();

    // Obtenir la référence au contrôleur de la fenêtre de dialogue et configurer la fenêtre
    SupprimerPropositionDialogController controller = loader.getController();
    Stage dialogStage = new Stage();
    controller.setDialogStage(dialogStage);

    // Afficher la fenêtre de dialogue et attendre que l'utilisateur interagisse avec elle
    dialogStage.setScene(new Scene(root));
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.showAndWait();

    // Si l'utilisateur a cliqué sur "Supprimer", supprimer la proposition
    if (controller.isOkClicked()) {
        
        int propositionId = controller.getPropositionId();
    ServiceProposition.delete(propositionId);

    // Afficher une alerte pour confirmer la suppression
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText("La proposition a été supprimée avec succès.");
    alert.showAndWait();
    
    
}
    }
    

    @FXML
    private void modifierProposition(ActionEvent event) {
        try {
            // Charger l'interface RechercheDemandeId.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RechercherPropositionId.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur pour RechercheDemandeIdController
            RechercherPropositionIdController controller = loader.getController();

            // Configurer la fenêtre avec la scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Rechercher une proposition");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface RechercherPropositionId.fxml: " + ex.getMessage());
        }
    }

    @FXML
    private void ajouterProposition(ActionEvent event) throws IOException {
        Parent ajouterPropositionParent = FXMLLoader.load(getClass().getResource("AjouterProposition.fxml"));
    Scene ajouterPropositionScene = new Scene(ajouterPropositionParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(ajouterPropositionScene);
    window.show();
    }
    
}
