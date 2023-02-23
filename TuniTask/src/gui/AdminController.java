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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author abdes
 */
public class AdminController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Button valid;
    @FXML
    private Button annule;
    @FXML
    private Button Myprofile;
    @FXML
    private Button logout;
    @FXML
    private Label Clients;
    @FXML
    private Label Freelancers;
    @FXML
    private Label Organizateurs;
    @FXML
    private Label Admins;
    @FXML
    private TableView<?> Table;
    @FXML
    private Button Desactive;
    @FXML
    private Button Supprime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signup(ActionEvent event) {
    }

    @FXML
    private void AllerAccueil(MouseEvent event) {
    }

    @FXML
    private void ValideClicked(ActionEvent event) {
    }

    @FXML
    private void AnnuleClicked(MouseEvent event) {
    }
    
}
