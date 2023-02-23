/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sample;

import entite.Demande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import service.ServiceDemande;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class AfficherDemandesController implements Initializable {

    @FXML
    private TableView<?> tableView;
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
    private TableColumn<Demande, Integer> delaiColumn;

    @FXML
    private TableColumn<Demande, String> langageColumn;

    @FXML
    private TableColumn<Demande, String> createdAtColumn;

    @FXML
    private Button fermerButton;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button afficherButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    private ServiceDemande serviceDemande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    public void setServiceDemande(ServiceDemande serviceDemande) {
        this.serviceDemande = serviceDemande;

        // configure the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        delaiColumn.setCellValueFactory(new PropertyValueFactory<>("delai"));
        langageColumn.setCellValueFactory(new PropertyValueFactory<>("langage"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        // populate the table view with the list of demandes
        tableView.setItems((ObservableList<Demande>) serviceDemande.readAll());
    }

    @FXML
    void fermer() {
        // close the window
        Stage stage = (Stage) fermerButton.getScene().getWindow();
        stage.close();
    }
}