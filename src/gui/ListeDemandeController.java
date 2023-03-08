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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.ServiceDemande;
import service.ServiceProposition;
import utile.DataSource;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mohamed gabsi
 */
public class ListeDemandeController implements Initializable {
 private Label label;
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
    private Button afficherButton;
     @FXML
    private Button retourButton;
     PropositionListController interfaceProposition;
private ObservableList<Demande> demandesList;
    private ServiceDemande ServiceDemande;
    @FXML
    private Button ajouterProposition;
    @FXML
    private TextField inputRech;
    @FXML
    private Button pdf2;
 public ObservableList<Demande> list;
    @FXML
    private Button trié;
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
         ServiceDemande pss = new ServiceDemande();
            try {
            list = FXCollections.observableArrayList(
                    pss.readAll()
            );        
        
        
   FilteredList<Demande> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Demande>) Demandes -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        String tostring = Demandes.getEmail();
                        if (tostring.toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Demande> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableDemande.comparatorProperty());
                tableDemande.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
    }    

    @FXML
    private void afficherDemandes(ActionEvent event) throws IOException {
        // Get selected demande from table
    Demande selectedDemande = tableDemande.getSelectionModel().getSelectedItem();

    if (selectedDemande != null) {
        // Load FXML file for DetailsDemande.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDemande.fxml"));
        Parent root = loader.load();

        // Get controller for DetailsDemande.fxml and pass selectedDemande to it
        DetailsDemandeController controller = loader.getController();
        controller.setDemande(selectedDemande);

        // Display DetailsDemande.fxml
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    }
   @FXML
    private void retourProposition(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PropositionList.fxml"));
    Parent root = loader.load();
    PropositionListController interfaceProposition = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    private void consulterTable(ActionEvent event) {
        Stage currentStage = (Stage) retourButton.getScene().getWindow();
        currentStage.close();
        
        // Ouvrir l'interface InterfaceDemande.fxml
        PropositionListController interfaceProposition = new PropositionListController();
        //controller.showInterfaceDemande();
    }
    


 

     private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("..\\ListProposition.pdf"));
        d.open();
        
        PdfPTable pTable = new PdfPTable(3);
       

     
        
        tableDemande.getItems().forEach((t) -> {
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

    @FXML
    private void trié(ActionEvent event) {
            ServiceDemande cs = new ServiceDemande();
        List<Demande> listrec = new ArrayList<>();
        listrec = cs.readAllBySalaire();
        ObservableList<Demande> data = FXCollections.observableArrayList(listrec);
        tableDemande.setItems(data);
        
    }
   @FXML
    private void ajouterButton(ActionEvent event) throws MessagingException {
             ServiceProposition productService = new ServiceProposition();     
   Proposition ccc = new Proposition( tableDemande.getSelectionModel().getSelectedItem().getId(),1 );
                    
       System.out.println("///////// "+tableDemande.getSelectionModel().getSelectedItem().getId());
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                 productService.insert(ccc);
      sendMail("gabsilaroussi99@gmail.com", "Proposition Crée avec succées", 
                    "Proposition ajoutée avec succées");
          TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouté avec succés");
            tray.setMessage("LA proposition a été ajouté avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
          public static void sendMail(String recipient,String Subject,String Text) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "schoolesprit1@gmail.com";
        String password = "fuwwxlscxbnfirok";
        //String myAccountEmail = "mohamedgabsi99@outlook.fr";
       // String password = "mlg12345";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient,Subject,Text);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String Subject,String Text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(Subject);
            message.setText(Text);
            return message;
        } catch (MessagingException ex) {
          
        }
        return null;}


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



