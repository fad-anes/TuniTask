/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entite.Demande;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class InterfaceDemandeController implements Initializable {

    @FXML
    private TableView<Demande> tableDemande;
    @FXML
    private TableColumn<Demande, Integer> idColumn;
    @FXML
    private TableColumn<Demande, Integer> clientIdColumn;
    @FXML
    private TableColumn<Demande, String> titreColumn;
    @FXML
    private TableColumn<Demande, String> descriptionColumn;
    @FXML
    private TableColumn<Demande, Double> salaireColumn;
    @FXML
    private TableColumn<Demande, String> delaiColumn;
    @FXML
    private TableColumn<Demande, String> langageColumn;
    @FXML
    private TableColumn<Demande, String> createdAtColumn;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button afficherButton;
    
    private ObservableList<Demande> demandesList;
    private ServiceDemande ServiceDemande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        demandesList = FXCollections.observableArrayList();
        ServiceDemande = new ServiceDemande();
        
        // Configure table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        delaiColumn.setCellValueFactory(new PropertyValueFactory<>("delai"));
        langageColumn.setCellValueFactory(new PropertyValueFactory<>("langage"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        
        // Populate table with data from database
        demandesList.addAll(ServiceDemande.readAll());
        tableDemande.setItems(demandesList);
    }    

    @FXML
    private void ajouterDemande(ActionEvent event) throws IOException {
    Parent ajouterDemandeParent = FXMLLoader.load(getClass().getResource("AjouterDemande.fxml"));
    Scene ajouterDemandeScene = new Scene(ajouterDemandeParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(ajouterDemandeScene);
    window.show();
    }
public void populateTableDemande() {
    ObservableList<Demande> demandes = FXCollections.observableArrayList();
    ServiceDemande serviceDemande = new ServiceDemande();
    List<Demande> demandeList = serviceDemande.readAll();
    demandes.addAll(demandeList);
    tableDemande.setItems(demandes);
}
    @FXML
    private void modifierDemande(ActionEvent event) {
        
    try {
            // Charger l'interface RechercheDemandeId.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RechercheDemandeId.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur pour RechercheDemandeIdController
            RechercheDemandeIdController controller = loader.getController();

            // Configurer la fenêtre avec la scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Rechercher une demande");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface RechercheDemandeId.fxml: " + ex.getMessage());
        }
    }


    @FXML
    private void supprimerDemande(ActionEvent event) {
        // Get selected demande from table
        Demande selectedDemande = tableDemande.getSelectionModel().getSelectedItem();
        
        if (selectedDemande != null) {
            // Remove demande from database and table
            ServiceDemande.delete(selectedDemande);
            demandesList.remove(selectedDemande);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("La demande a été supprimée avec succès.");
            alert.showAndWait();
        }
    }

    @FXML
    private void afficherDemandes(ActionEvent event) {
        // Refresh table with data from database
        demandesList.clear();
        demandesList.addAll(ServiceDemande.readAll());
        tableDemande.setItems(demandesList);
    }
  
}

