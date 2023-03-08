/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class SupprimerPropositionDialogController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private Button annulerButton;
    @FXML
    private Button supprimerButton;

    private Stage dialogStage;
    private int propositionId;
    private boolean okClicked = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void annuler(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        // Vérifier si l'ID est un entier
        try {
            propositionId = Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            // Afficher un message d'erreur si l'ID n'est pas un entier valide
            // ...
            return;
        }
        
        // Fermer la fenêtre de dialogue
        okClicked = true;
        dialogStage.close();
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public int getPropositionId() {
        return propositionId;
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}
