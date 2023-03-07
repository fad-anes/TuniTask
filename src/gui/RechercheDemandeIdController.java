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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * Permet de vérifier si l'id d'une demande existe ou non. S'il existe, l'utilisateur est redirigé
 * vers l'interface ModifierDemande.fxml pour modifier la demande correspondante.
 *
 * @author mohamed
 */
public class RechercheDemandeIdController implements Initializable {

    @FXML
    private TextField id_demande;
    @FXML
    private Button btn_rechercher;
    private ServiceDemande serviceDemande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         serviceDemande = new ServiceDemande();
    }

    /**
     * Recherche une demande dans la base de données en fonction de l'id fourni.
     * Si une demande avec cet id existe, redirige vers l'interface de modification.
     */
    @FXML
    private void rechercherDemande(ActionEvent event) throws IOException {
     String id = id_demande.getText();
    try {
        int idInt = Integer.parseInt(id);
        serviceDemande = new ServiceDemande();
        Demande demande = serviceDemande.readById(idInt);
        if (demande != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierDemande.fxml"));
            Parent root = loader.load();
            ModifierDemandeController controller = loader.getController();
            controller.setDemande(demande, idInt); // Passer idInt comme paramètre
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La demande avec l'id " + id + " n'existe pas.");
            alert.showAndWait();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'id doit être un nombre entier.");
        alert.showAndWait();
    }
}}