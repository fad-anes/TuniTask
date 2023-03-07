/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Services.ServiceReclamation;
import cnx_DB.DataSource;
import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddreclamationController implements Initializable {
    
   // public static final String ACCOUNT_SID = "AC5ec63110ff29fcf70a4fea942a5f09b9"; 
   // public static final String AUTH_TOKEN = "[AuthToken]"; 

    @FXML
    private TextField nomf;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField emailf;
    @FXML
    private TextField descriptionf;
           Stage stage;
    PreparedStatement ste;
    Connection mc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addRec(ActionEvent event) throws SQLException, MessagingException {
         String nom = nomf.getText();
        String prenom = prenomf.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String email = emailf.getText();
        String description = descriptionf.getText();
        
     
         if (nom.isEmpty() || prenom.isEmpty()|| email.isEmpty()|| description.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             Reclamation r=new Reclamation(nom,prenom,email,description);
             ServiceReclamation rc = new ServiceReclamation(); 
             rc.ajouterReclamation(r);
             //sendSMS.sendSMS();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Reclamation Ajoutée avec succes!");
                alert.showAndWait();
                
           
 
           nomf.setText(null);
          prenomf.setText(null);
          emailf.setText(null);
          descriptionf.setText(null);
         }
         refresh();
         sendMail("hadhemi.omrani@esprit.tn", "Votre réclamation a été envoyé avec succés", 
                    "Votre réclamation a été envoyé avec succés");
                               TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Envoyé avec succés");
            tray.setMessage("Envoyé avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
         
    }
          

    @FXML
    private void editRec(ActionEvent event) {
    }
    
    
    
    @FXML
    void Listreclamationbtn(ActionEvent event) {
       try {
                     Parent root = FXMLLoader.load(getClass().getResource("TableReclamation.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
         
                        

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }
      
       

    @FXML
    private void deleteRec(ActionEvent event) {
        
    }
    
    public static void sendMail(String recipient,String Subject,String Text) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "isitapp11@gmail.com";
        String password = "cdjgkltnjmzbrlhj";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient,Subject,Text);

        javax.mail.Transport.send(message);
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
    
    
   
}
