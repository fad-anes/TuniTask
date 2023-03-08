/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.PasswordHasher;
import entite.Role;
import entite.Users;
import static gui.InscrireController.isAlpha;
import static gui.InscrireController.isNumerique;
import static gui.InscrireController.verfierEmail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ServiceRole;
import service.ServiceUsers;

/**
 * FXML Controller class
 *
 * @author abdes
 */
public class CompteController implements Initializable {

    @FXML
    private Label daten;


    @FXML
    private TextField annee;

    @FXML
    private TextField competence;

    @FXML
    private PasswordField cpwd;

    @FXML
    private DatePicker date;

    @FXML
    private TextField email;

    @FXML
    private TextField entreprise;

    @FXML
    private Pane erreur;


    @FXML
    private Label lLangage;

    @FXML
    private TextField langage;

    @FXML
    private Label lannee;

    @FXML
    private Label lcompetence;

    @FXML
    private Label lcpwd;

    @FXML
    private Label lentreprise;

    @FXML
    private Label lpassword;

    @FXML
    private TextField nom;

    @FXML
    private Button non;

    @FXML
    private Button ouiBTN;


    @FXML
    private TextField prenom;

    @FXML
    private Pane reussi;


    @FXML
    private Pane warrning;
        @FXML
    private TextField passwordA;

   File selectedFile =null ;
    ServiceUsers u ;
    ServiceRole r ;
    @FXML
    private Label lnom;
    @FXML
    private Label lemail;
    @FXML
    private Label lprenom;
    
    private ImageView Image;
    @FXML
    private Pane PaneValid;
    @FXML
    private Pane warrning1;
    @FXML
    private Label changernom;
    @FXML
    private Label changerprenom;
    @FXML
    private Label changeremail;
    @FXML
    private Label changeredate;
    @FXML
    private Label changerentreprise;
    @FXML
    private Label changercompetence;
    @FXML
    private Label changereanne;
    @FXML
    private Label changerlangage;
    @FXML
    private Pane PanePWD;
    @FXML
    private Button ValiderPWD;
    @FXML
    private Button AnnulerPWD;
    @FXML
    private Label lpassword1;
    @FXML
    private ImageView IMAGE;
    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Label lChagerPwd;
    @FXML
    private TextField NpasswordA;
    @FXML
    private TextField NpasswordAv;
    @FXML
    private ImageView image1;
    @FXML
    private Label demande;
    @FXML
    private Label evenement;
    @FXML
    private Label quiz;
    @FXML
    private Label reclamation;
    @FXML
    private Label offre;
    Users user;
    Role role;
    @FXML
    private Label userM;
    void init(){
        // TODO
        u=new ServiceUsers();
        r=new  ServiceRole();
       PanePWD.setVisible(false);
       warrning1.setVisible(false);
       warrning.setVisible(false);
       erreur.setVisible(false);
       reussi.setVisible(false);
        // TODO
       PaneValid.setVisible(false);
          user = u.readById(UserSession.getInstance().getId().intValue());
          System.out.println(user);
           //user = u.readById(49);
         role= r.Role_By_Id_user(user.getId());
       
       
     //  Image image = new Image(user.getSrcimage());
       
       
     //  IMAGE.setImage(image);
       nom.setText(user.getFirstName());
       prenom.setText(user.getLastName());
       date.setValue(LocalDate.parse(user.getDateOfBirth().toString()));
       email.setText(user.getEmail());
      // passwordA.setText(user.getPassword());
       //passwordA.setVisible(false);
       if (role.getRoleName().equals("Freelancer"))
       {   
         competence.setText(role.getSkills());
         langage.setText(role.getLangage());
         annee.setText(role.getExperience());
         entreprise.setVisible(false);
         lentreprise.setVisible(false);
         changerentreprise.setVisible(false);
       }
       else if (role.getRoleName().equals("Organizateur"))
       {
            competence.setVisible(false);
         langage.setVisible(false);
         annee.setVisible(false);
         lannee.setVisible(false);
         lLangage.setVisible(false);
         lcompetence.setVisible(false);
         changerentreprise.setVisible(true);
         entreprise.setText(role.getEntreprise());
         changercompetence.setVisible(false);
         changereanne.setVisible(false);
         changerlangage.setVisible(false);
         ajusterLayout(80);
         entreprise.setLayoutY(entreprise.getLayoutY() + 80);
          lentreprise.setLayoutY(lentreprise.getLayoutY() + 80); 
          changerentreprise.setLayoutY(changerentreprise.getLayoutY() + 80); 
       }
       else{ competence.setVisible(false);
         langage.setVisible(false);
         annee.setVisible(false);
         lannee.setVisible(false);
         lLangage.setVisible(false);
         lcompetence.setVisible(false); 
         entreprise.setVisible(false);
         lentreprise.setVisible(false);
         changerentreprise.setVisible(false);
         changercompetence.setVisible(false);
         changereanne.setVisible(false);
         changerlangage.setVisible(false);
         ajusterLayout(100);
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      init();
      
     
    }    
    void ajusterLayout(int x)
    {  lnom.setLayoutY(lnom.getLayoutY() + x);
         lprenom.setLayoutY(lprenom.getLayoutY() + x);
         lemail.setLayoutY(lemail.getLayoutY() + x);
         nom.setLayoutY(nom.getLayoutY() + x);
         prenom.setLayoutY(prenom.getLayoutY() + x);
         email.setLayoutY(email.getLayoutY() + x);
         daten.setLayoutY(daten.getLayoutY() + x);
         date.setLayoutY(date.getLayoutY() + x);
         changernom.setLayoutY(changernom.getLayoutY() + x);
         changerprenom.setLayoutY(changerprenom.getLayoutY() + x);
         changeremail.setLayoutY(changeremail.getLayoutY() + x);
         changeredate.setLayoutY(changeredate.getLayoutY() + x);
    }


    @FXML
    private void verifierpassword(KeyEvent event) {
    }

    @FXML
    private void AnnulerBtn(MouseEvent event) {
         PaneValid.setVisible(false);
       nom.setDisable(true);
       prenom.setDisable(true);
       email.setDisable(true);
       date.setDisable(true);
       entreprise.setDisable(true);
        competence.setDisable(true);
         annee.setDisable(true);
          langage.setDisable(true);
          reussi.setVisible(false);
           warrning1.setVisible(false);
       warrning.setVisible(false);
       erreur.setVisible(false);
          //init();
       
    }

   
    void ModifierBTN(MouseEvent event) {

    }
Boolean Bdate=true;
    String img ;
    @FXML
    private void ouiClicked(MouseEvent event) {
         LocalDate date1 = date.getValue();
        LocalDate currentDate = LocalDate.now();
        
        if (ChronoUnit.YEARS.between(date1, currentDate)< 18)
        {Bdate=false;
         date.setStyle("-fx-background-color: #CB0000");
        }
        else
        {Bdate=true;
        }
   if (Bmail && Bnom && Bprenom && Bexperience && Bdate )
 
   {
       
        System.out.println(date.getValue());
        //Integer s =su.getIdByEmail(email.getText());
           
           System.out.println(nom.getText());
             user.setFirstName(nom.getText());
           
         user.setDateOfBirth(  Date.valueOf(date.getValue()));
         user.setLastName(prenom.getText());
         user.setEmail(email.getText());
         
          if (role.getRoleName().equals("Freelancer"))
       {
       
            role.setExperience(annee.getText());
            role.setLangage(langage.getText());
            role.setSkills(competence.getText());
       }  
          if (role.getRoleName().equals("Organisateur"))
          {
          role.setEntreprise(entreprise.getText());
          }
          if(selectedFile != null){
       String destinationFolderPath = "C:\\xampp\\htdocs\\img";
       Path sourcePath = Paths.get(selectedFile.toURI());
              System.out.println(sourcePath);
       img= "C:/xampp/htdocs/img/"+String.valueOf(user.getEmail())+sourcePath.getFileName();
                 System.out.println(Paths.get(img));
       File destinationFolder = new File(destinationFolderPath);
       try {
       if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
            Files.deleteIfExists(Paths.get(img));
            Files.copy(sourcePath, destinationFolder.toPath().resolve(String.valueOf(user.getEmail())+sourcePath.getFileName()));

            System.out.println("L'image a été copiée avec succès dans le dossier de destination.");
        } 
       catch (IOException e) {
            e.printStackTrace();
        } }
          else{
                           img="C:/xampp/htdocs/img/logo.png";
                           }
          user.setSrcimage(img);
          u.update(user);
          r.update(role);
          date.setStyle("-fx-background-color: #00FF51");
           warrning1.setVisible(false);
       warrning.setVisible(false);
       erreur.setVisible(false);
       reussi.setVisible(true);
   }
   else{
       System.out.println("eeeeeeeeeer");
        warrning1.setVisible(false);
       warrning.setVisible(false);
       erreur.setVisible(true);
       reussi.setVisible(false);
   }
       
        
    }

    @FXML
    private void nonClicked(MouseEvent event) {
         reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         PaneValid.setVisible(false);
    }

    @FXML
    private void changerNom(MouseEvent event) {
       PaneValid.setVisible(true);
       nom.setDisable(false);
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);

       
    }

    @FXML
    private void CacherWarnning1(MouseEvent event) {
         warrning1.setVisible(false);
         
                    

    }

    @FXML
    private void AfficherWarnning1(MouseEvent event) {
         warrning1.setVisible(true);
       
        
                     

    }

    @FXML
    private void changerPrenom(MouseEvent event) {
        PaneValid.setVisible(true);
       prenom.setDisable(false);
                   warrning.setVisible(false);
                    PaneValid.setVisible(true);
       
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);

    }

    @FXML
    private void changerEmail(MouseEvent event) {
            email.setDisable(false);
            PaneValid.setVisible(true);
            warrning.setVisible(false);
             PaneValid.setVisible(true);
       
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);
    }

    @FXML
    private void changerDate(MouseEvent event) {
        date.setDisable(false);
        PaneValid.setVisible(true);
         PaneValid.setVisible(true);
       
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);
    }

    @FXML
    private void changerEntreprise(MouseEvent event) {
        entreprise.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
         PaneValid.setVisible(true);
       
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);
        
    }

    @FXML
    private void changerCompetence(MouseEvent event) {
        competence.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
         PaneValid.setVisible(true);
      
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);
    }

    @FXML
    private void changereAnne(MouseEvent event) {
        annee.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
         PaneValid.setVisible(true);
       
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);
    }

    @FXML
    private void changerLangage(MouseEvent event) {
        langage.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
         PaneValid.setVisible(true);
       
       reussi.setVisible(false);
         warrning1.setVisible(false);
         warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
       warrning.setVisible(false);
    }

    @FXML
    private void ValiderBTN(MouseEvent event) {
        PaneValid.setVisible(false);
        warrning.setVisible(true);
         PaneValid.setVisible(true);
       
       reussi.setVisible(false);
         warrning1.setVisible(false);
         //warrning.setVisible(false);
         erreur.setVisible(false);
         //PaneValid.setVisible(false);
     //  warrning.setVisible(false);
    }

 Boolean Bnom =true;
    @FXML
    private void VNom(KeyEvent event) {
       
       
        if(nom.getText().equals(""))
        {
           nom.setStyle("-fx-background-color: #00FF51");
                   Bnom =false;
        }
           if (isAlpha(nom.getText())) {
                nom.setStyle("-fx-background-color: #00FF51");
                Bnom =true;
                } else {
                        {nom.setStyle("-fx-background-color: #CB0000");
                         Bnom =false;
                        }
                         }
            if(nom.getText().length()==0)
         {
              System.out.println(nom.getText().length()+"#");
            nom.setStyle("-fx-background-color: #CB0000");
                         Bnom =false;
                        }
    }
    
 Boolean Bprenom =true;
    @FXML
    private void VPrenom(KeyEvent event) {

       
       
        if(prenom.getText().equals(""))
        {
           prenom.setStyle("-fx-background-color: #00FF51");
                   Bprenom =false;
        }
           if (isAlpha(prenom.getText())) {
                prenom.setStyle("-fx-background-color: #00FF51");
                Bprenom =true;
                } else {
                        {prenom.setStyle("-fx-background-color: #CB0000");
                         Bprenom =false;
                        }
                         }
            if(prenom.getText().length()==0)
         {
              System.out.println(prenom.getText().length()+"#");
            prenom.setStyle("-fx-background-color: #CB0000");
                         Bprenom =false;
                        }
    
    }
Boolean Bmail=true;
    @FXML
    private void VEmail(KeyEvent event) {

       
       
        if(email.getText().equals(""))
        {
           email.setStyle("-fx-background-color: #00FF51");
                   Bmail =false;
        }
           if (verfierEmail(email.getText())) {
                email.setStyle("-fx-background-color: #00FF51");
                Bmail =true;
                } else {
                        {email.setStyle("-fx-background-color: #CB0000");
                         Bmail =false;
                        }
                         }
            if(email.getText().length()==0)
         {
              System.out.println(email.getText().length()+"#");
            email.setStyle("-fx-background-color: #CB0000");
                         Bmail =false;
                        }
    
    }
Boolean Bexperience=true;
    @FXML
    private void Vexperience(KeyEvent event) {
                if (isNumerique(annee.getText().toString())) {
    annee.setStyle("-fx-background-color: #00FF51");
   Bexperience =true;
    
} else 
    {annee.setStyle("-fx-background-color: #CB0000");
       Bexperience =false;
    }
                }   

    @FXML
    private void AjouterImage(MouseEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers image", "*.jpg", "*.png", "*.gif")
        );
          Stage primaryStage = new Stage();
     selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            // Création de l'image à partir du fichier sélectionné
            Image image = new Image(selectedFile.toURI().toString());
        IMAGE.setImage(image);
        PaneValid.setVisible(true);
    }
}

    @FXML
    private void ChangerMotdepasse(MouseEvent event) {
        lChagerPwd.setVisible(false);
        PanePWD.setVisible(true);
    }

    @FXML
    private void AnnulePWDClicked(MouseEvent event) {
        lChagerPwd.setVisible(true);
        PanePWD.setVisible(false);
        passwordA.setText("");
        NpasswordA.setText("");
        NpasswordAv.setText("");
        
    }

    @FXML
    private void ValiderPwdClicked(MouseEvent event) {
        System.out.println(user.getPassword());
         PasswordHasher ph = new PasswordHasher();
        if ( !ph.hashPassword(passwordA.getText()).equals(user.getPassword()))
        {
            
             warrning1.setVisible(false);
       warrning.setVisible(false);
       erreur.setVisible(true);
       reussi.setVisible(false);
        }
        else{
           
          if (NpasswordA.getText() != null && !NpasswordA.getText().equals(""))
          if (NpasswordAv.getText() != null && !NpasswordAv.getText().equals(""))
          {
                          if(!NpasswordAv.getText().equals(NpasswordA.getText()))
                          {
                                  System.out.println("erreur1");
                                  warrning1.setVisible(false);
                                  warrning.setVisible(false);
                                  erreur.setVisible(true);
                                  reussi.setVisible(false);
                          }
                          else
                          {
                             
                             
                             user.setPassword(ph.hashPassword(NpasswordAv.getText()));
                             u.update(user);
                             warrning1.setVisible(false);
                             warrning.setVisible(false);
                             erreur.setVisible(false);
                             reussi.setVisible(true);
                          }
          }
        }
        
        
    }

    @FXML
    private void User(MouseEvent event) {
               ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
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

    @FXML
    private void Demande(MouseEvent event) {
    }

    @FXML
    private void evénement(MouseEvent event) {
    }

    @FXML
    private void quiz(MouseEvent event) throws IOException {
       // ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("displayquizs.fxml"));
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

    @FXML
    private void reclamation(MouseEvent event) {
    }

    @FXML
    private void offre(MouseEvent event) {
        // ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("internautevoir.fxml"));
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

    @FXML
    private void Profile(ActionEvent event) {
    }

    @FXML
    private void log_out(ActionEvent event) {
            ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
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