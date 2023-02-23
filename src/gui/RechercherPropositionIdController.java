/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

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
import service.ServiceProposition;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class RechercherPropositionIdController implements Initializable {

    @FXML
    private TextField id_proposition;
    @FXML
    private Button btn_rechercher;
    private ServiceProposition serviceProposition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         serviceProposition = new ServiceProposition();
    }    

    @FXML
    private void rechercherProposition(ActionEvent event) throws IOException {
        String id = id_proposition.getText();
    try {
        int idInt = Integer.parseInt(id);
        serviceProposition = new ServiceProposition();
        Proposition proposition = serviceProposition.readById(idInt);
        if (proposition != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProposition.fxml"));
            Parent root = loader.load();
            ModifierPropositionController controller = loader.getController();
            controller.setProposition(proposition, idInt); // Passer idInt comme paramètre
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La proposition avec l'id " + id + " n'existe pas.");
            alert.showAndWait();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'id doit être un nombre entier.");
        alert.showAndWait();
    }
}
    }
   
