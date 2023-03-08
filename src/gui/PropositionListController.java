/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.Demande;
import entite.Proposition;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceProposition;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class PropositionListController implements Initializable {

    @FXML
    private TableView<Proposition> propositionTable;
    @FXML
    private TableColumn<Proposition, Integer> idColumn;
    @FXML
    private TableColumn<Proposition, Demande> idDemandeColumn;
    @FXML
    private TableColumn<Proposition, String> firstnameColumn;
    @FXML
    private TableColumn<Proposition, String> lastnameColumn;
    @FXML
    private TableColumn<Proposition, String> emailColumn;
    @FXML
    private Button afficherButton;
    @FXML
    private Button consulterButton;
    @FXML
    private Button supprimerButton;
    private ObservableList<Proposition> propositionsList;
    private ServiceProposition ServiceProposition;
    
    
 public ObservableList<Proposition> list;
 private Label label;
    @FXML
    private TextField inputRech;
    @FXML
    private Button pdf2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
        propositionsList = FXCollections.observableArrayList();
        ServiceProposition = new ServiceProposition();
        
        // Configure table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idDemandeColumn.setCellValueFactory(new PropertyValueFactory<>("id_demande"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        // Populate table with data from database
        propositionsList.addAll(ServiceProposition.readAll());
        propositionTable.setItems(propositionsList);
         ServiceProposition pss = new ServiceProposition();
            try {
            list = FXCollections.observableArrayList(
                    pss.readAll()
            );        
        
        
   FilteredList<Proposition> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Proposition>) Propositions -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        String tostring = Propositions.getNom();
                        if (tostring.toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Proposition> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(propositionTable.comparatorProperty());
                propositionTable.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
    }    

    @FXML
    private void afficherPropositions(ActionEvent event) {
        propositionsList.clear();
        propositionsList.addAll(ServiceProposition.readAll());
        propositionTable.setItems(propositionsList);
    }

    @FXML
    private void consulterDemandes(ActionEvent event) throws IOException {
        Parent ajouterDemandeParent = FXMLLoader.load(getClass().getResource("ListeDemande.fxml"));
    Scene ajouterDemandeScene = new Scene(ajouterDemandeParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(ajouterDemandeScene);
    window.show();
    }

    @FXML
    private void supprimerProposition(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerProposition.fxml"));
    Parent root = loader.load();

    // Obtenir la référence au contrôleur de la fenêtre de dialogue et configurer la fenêtre
    SupprimerPropositionDialogController controller = loader.getController();
    Stage dialogStage = new Stage();
    controller.setDialogStage(dialogStage);

    // Afficher la fenêtre de dialogue et attendre que l'utilisateur interagisse avec elle
    dialogStage.setScene(new Scene(root));
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.showAndWait();

    // Si l'utilisateur a cliqué sur "Supprimer", supprimer la proposition
    if (controller.isOkClicked()) {
        
        int propositionId = controller.getPropositionId();
    ServiceProposition.delete(propositionId);

    // Afficher une alerte pour confirmer la suppression
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText("La proposition a été supprimée avec succès.");
    alert.showAndWait();
          TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimé avec succés");
            tray.setMessage("Supprimé avec succés");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
    
}
    }
 public void resetTableData() throws SQLDataException, SQLException {
        ServiceProposition cs = new ServiceProposition();
        List<Proposition> listrec = new ArrayList<>();
        listrec = cs.readAll();
        ObservableList<Proposition> data = FXCollections.observableArrayList(listrec);
        propositionTable.setItems(data);
    }
     private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("..\\ListProposition.pdf"));
        d.open();
        
        PdfPTable pTable = new PdfPTable(3);
       

     
        
        propositionTable.getItems().forEach((t) -> {
       pTable.addCell(String.valueOf(t.getNom()));
         pTable.addCell(String.valueOf(t.getPrenom()));
             pTable.addCell(String.valueOf(t.getEmail()));



       
        });
        
                        d.add(pTable);

        d.close();
        Desktop.getDesktop().open(new File("..\\ListProposition.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            
   
        }
    
}


    public void retourBtn(ActionEvent event) {
        Parent root;
        try {
            ( (Node) event.getSource()).getScene().getWindow().hide();
            root = FXMLLoader.load(getClass().getResource("Freelancer.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TuniTask");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TuniTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}




