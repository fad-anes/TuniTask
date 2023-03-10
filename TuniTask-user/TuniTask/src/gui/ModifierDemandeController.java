/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import entite.Demande;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceDemande;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifierDemandeController implements Initializable {

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
    private Button modifierButton;
    @FXML
     private Button retourButton;
   
    

    private ServiceDemande ServiceDemande;
    
    // Ajouter un champ pour l'ID
    private int idInt;

    public void setDemande(Demande demande, int idInt) {
        this.idInt = idInt;
        //clientIdField.setText(String.valueOf(demande.getId_client()));
        titreField.setText(demande.getTitre());
        descriptionField.setText(demande.getDescription());
        salaireField.setText(demande.getSalaire());
        delaiField.setText(demande.getDelai());
        langageField.setText(demande.getLangage());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceDemande = new ServiceDemande();
    }

    
    @FXML
    private void modifierDemande(ActionEvent event) {
        //int clientId = Integer.parseInt(clientIdField.getText());
        String titre = titreField.getText();
        String description = descriptionField.getText();
        String salaire = salaireField.getText();
        String delai = delaiField.getText();
        String langage = langageField.getText();
        LocalDateTime now = LocalDateTime.now();
        Timestamp ls = Timestamp.valueOf(now);
        if (titre.isEmpty() || description.isEmpty() || salaire.isEmpty() || delai.isEmpty() || langage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            Demande demande = new Demande();
            //demande.setId_client(clientId);
            demande.setTitre(titre);
            demande.setDescription(description);
            demande.setSalaire(salaire);
            demande.setDelai(delai);
            demande.setLangage(langage);
            demande.setCreated_at(ls);

            ServiceDemande.update(demande, idInt);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succ??s");
        alert.setHeaderText(null);
        alert.setContentText("La demande a ??t?? modifi??e avec succ??s.");
        alert.showAndWait();
        try{
        clientIdField.clear();
            titreField.clear();
            descriptionField.clear();
            salaireField.clear();
            delaiField.clear();
            langageField.clear();
    }
        catch(Exception e){
            System.out.println("");
        }
        }

}

public void retourAuTable(ActionEvent event) throws IOException {
     Stage currentStage = (Stage) retourButton.getScene().getWindow();
        currentStage.close();
    
}
   
    
}

