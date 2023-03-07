/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Demande;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class DemandeClientController implements Initializable {

    @FXML
    private TextField idClientTextField;
    @FXML
    private Button rechercherButton;
    @FXML
    private TableView<Demande> demandesTableView;
    @FXML
    private TableColumn<Demande, Integer> idDemandeColumn;
    @FXML
    private TableColumn<Demande, String> nomDemandeColumn;
    @FXML
    private TableColumn<Demande, String> prenomDemandeColumn;
    @FXML
    private TableColumn<Demande, String> emailDemandeColumn;
    @FXML private TableColumn<Demande, String> titreColumn;
    @FXML private TableColumn<Demande, String> descriptionColumn;
    @FXML private TableColumn<Demande, String> salaireColumn;
    @FXML private TableColumn<Demande, String> delaiColumn;
    @FXML private TableColumn<Demande, String> langageColumn;
    @FXML private TableColumn<Demande, String> created_atColumn;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private TextField filterField;
    private ObservableList<Demande> demandesList;
 private List<Demande> demandes = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
         demandesList = FXCollections.observableArrayList();
        filterField.setOnKeyReleased(event -> {
            String text = filterField.getText().trim();
            if (text.isEmpty()) {
                demandesTableView.setItems(demandesList);
            } else {
                Predicate<Demande> containsText = demande -> demande.getTitre().toLowerCase().contains(text.toLowerCase());
                ObservableList<Demande> filteredList = demandesList.filtered(containsText);
                demandesTableView.setItems(filteredList);
            }
        });
    }    
        

    @FXML
    private void rechercherDemandes(ActionEvent event) {
        int idClient = Integer.parseInt(idClientTextField.getText());
    ServiceDemande serviceDemande = new ServiceDemande();
    demandes = serviceDemande.readByIdClient(idClient);
    demandesTableView.getItems().setAll(demandes);
    }

    @FXML
    private void ajouterDemande(ActionEvent event) throws IOException {
         Parent ajouterDemandeParent = FXMLLoader.load(getClass().getResource("AjouterDemande.fxml"));
    Scene ajouterDemandeScene = new Scene(ajouterDemandeParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(ajouterDemandeScene);
    window.show();
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
        Demande selectedDemande = demandesTableView.getSelectionModel().getSelectedItem();
    if (selectedDemande == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une demande à supprimer!");
        alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette demande?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceDemande serviceDemande = new ServiceDemande();
            serviceDemande.delete(selectedDemande);
            demandesTableView.getItems().remove(selectedDemande);
        }
    }
    }
    
}



    


  

