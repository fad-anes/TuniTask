/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author abdes
 */
public class CompteController implements Initializable {

    @FXML
    private Button signupI;
    @FXML
    private Label IDacceuil;
    @FXML
    private TextArea nom;
    @FXML
    private TextArea pwd;
    @FXML
    private TextArea email;
    @FXML
    private PasswordField cpwd;
    @FXML
    private TextArea prenom;
    @FXML
    private ComboBox<?> role;
    @FXML
    private Label lentreprise;
    @FXML
    private TextArea entreprise;
    @FXML
    private Label lexperience;
    @FXML
    private TextArea experience;
    @FXML
    private ComboBox<?> skills;
    @FXML
    private Label lcode;
    @FXML
    private TextArea code;
    @FXML
    private DatePicker date;
    @FXML
    private Pane warrning;
    @FXML
    private Button ouiBTN;
    @FXML
    private Button non;
    @FXML
    private Pane reussi;
    @FXML
    private Pane erreur;
    @FXML
    private Pane erreur2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signin(MouseEvent event) {
    }

    @FXML
    private void AllerAccueil(MouseEvent event) {
    }

    @FXML
    private void CorrgerBackground(MouseEvent event) {
    }

    @FXML
    private void VNom(KeyEvent event) {
    }

    @FXML
    private void dblevpwd(KeyEvent event) {
    }

    @FXML
    private void VEmail(KeyEvent event) {
    }

    @FXML
    private void verifierpassword(KeyEvent event) {
    }

    @FXML
    private void CorrgerBackgroundP(MouseEvent event) {
    }

    @FXML
    private void VPrenom(KeyEvent event) {
    }

    @FXML
    private void initialize(ActionEvent event) {
    }

    @FXML
    private void CorrgerBackgroundE(MouseEvent event) {
    }

    @FXML
    private void Vexperience(KeyEvent event) {
    }

    @FXML
    private void AnnulerBtn(MouseEvent event) {
    }

    @FXML
    private void ValiderBTN(MouseEvent event) {
    }

    @FXML
    private void ouiClicked(MouseEvent event) {
    }

    @FXML
    private void nonClicked(MouseEvent event) {
    }
    
}
