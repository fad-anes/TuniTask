/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Demande;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class LesDemandesController implements Initializable {

    @FXML
    private TableView<Demande> tableDemande;
    @FXML
    private TableColumn<Demande, Integer> idColumn;
    @FXML
    private TableColumn<Demande, String> nomColumn;
    @FXML
    private TableColumn<Demande, String> prenomColumn;
    @FXML
    private TableColumn<Demande, String> emailColumn;
    @FXML
    private TableColumn<Demande, String> titreColumn;
    @FXML
    private TableColumn<Demande, String> descriptionColumn;
    @FXML
    private TableColumn<Demande, String> salaireColumn;
    @FXML
    private TableColumn<Demande, String> delaiColumn;
    @FXML
    private TableColumn<Demande, String> langageColumn;
    @FXML
    private TableColumn<Demande, String> createdAtColumn;
    
    @FXML
    private TextField filterField;
PropositionListController interfaceProposition;
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
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        //clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        delaiColumn.setCellValueFactory(new PropertyValueFactory<>("delai"));
        langageColumn.setCellValueFactory(new PropertyValueFactory<>("langage"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        
        // Populate table with data from database
        demandesList.addAll(ServiceDemande.readAll());
        tableDemande.setItems(demandesList);
        
        filterField.setOnKeyReleased(event -> {
            String text = filterField.getText().trim();
            if (text.isEmpty()) {
                tableDemande.setItems(demandesList);
            } else {
                Predicate<Demande> containsText = demande -> demande.getTitre().toLowerCase().contains(text.toLowerCase());
                ObservableList<Demande> filteredList = demandesList.filtered(containsText);
                tableDemande.setItems(filteredList);
            }
        });
    }    

    
    
}
