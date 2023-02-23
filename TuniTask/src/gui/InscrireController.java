package gui;

import entite.Role;
import entite.Users;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.ServiceRole;
import service.ServiceUsers;

public class InscrireController implements Initializable {

    @FXML
    private PasswordField cpwd;

    @FXML
    private TextArea email;

    @FXML
    private ImageView image;

    @FXML
    private TextArea nom;

    @FXML
    private TextArea prenom;

    @FXML
    private TextArea pwd;

    @FXML
    private ComboBox<String> role;
    @FXML
    private Label lentreprise;
    @FXML
    private TextArea entreprise;
    @FXML
    private Label lexperience;
    @FXML
    private TextArea experience;
    @FXML
    private ComboBox<String> skills;
    @FXML
    private Label lcode;
    @FXML
    private TextArea code;
    @FXML
    private Button signupI;
    @FXML
    private DatePicker date;
    @FXML
    private Label IDacceuil;
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
    @FXML
    private ImageView Ajouterimg;
    @FXML
    private ImageView IMAGE;
    File selectedFile =null ;
    @FXML
    void initialize(ActionEvent event) {
        System.out.println(role.getValue());
        switch(role.getValue()) {
  case "Client":
      skills.setVisible(false);
     experience.setVisible(false);
     lexperience.setVisible(false);
     entreprise.setVisible(false);
     lentreprise.setVisible(false);
      code.setVisible(false);
     lcode.setVisible(false);
    break;
  case "Freelancer":
    skills.setVisible(true);
     experience.setVisible(true);
     lexperience.setVisible(true);
     entreprise.setVisible(false);
     lentreprise.setVisible(false);
      code.setVisible(false);
     lcode.setVisible(false);
    break;
    case "Organizateur":
   skills.setVisible(false);
     experience.setVisible(false);
     lexperience.setVisible(false);
     entreprise.setVisible(true);
     lentreprise.setVisible(true);
      code.setVisible(false);
     lcode.setVisible(false);
      
    break;
  default:
     skills.setVisible(false);
     experience.setVisible(false);
     lexperience.setVisible(false);
     entreprise.setVisible(false);
     lentreprise.setVisible(false);
     code.setVisible(true);
     lcode.setVisible(true);
}
            
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     role.setItems(FXCollections.observableArrayList("Client","Freelancer","Organizateur","Admin"));
     skills.setItems(FXCollections.observableArrayList("FullStack","FrontEnd","BackEnd"));
     skills.setVisible(false);
     experience.setVisible(false);
     lexperience.setVisible(false);
     entreprise.setVisible(false);
     lentreprise.setVisible(false);
     code.setVisible(false);
     lcode.setVisible(false);
     warrning.setVisible(false);
     reussi.setVisible(false);
     erreur.setVisible(false);
     erreur2.setVisible(false);
     
    }
     Boolean Bpwd =null;
    @FXML
    private void verifierpassword(KeyEvent event) {
        String txt = pwd.getText();
        String ptxt = cpwd.getText();
        if (txt.equals(ptxt))
        {cpwd.setStyle("-fx-background-color: #D2FFC0");
        Bpwd =true;
        }
        else
        {cpwd.setStyle("-fx-background-color: #FFBBAC");
         Bpwd =false;
        }
    }
   
    @FXML
    private void dblevpwd(KeyEvent event) {
        if (cpwd.getText()!=null)
        {cpwd.setText(null);
        cpwd.setStyle("-fx-background-color: #FFFFFF");
    }}

public static boolean verfierEmail(String email) {
    // La regex pour vérifier l'adresse e-mail
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                        "[a-zA-Z0-9_+&*-]+)*@" + 
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                        "A-Z]{2,7}$";
                          
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
Boolean Bmail =null;
    @FXML
    private void VEmail(KeyEvent event) {
        if (verfierEmail(email.getText().toString())) {
    email.setStyle("-fx-background-color: #00FF51");
    Bmail =true ;
} else {
    {email.setStyle("-fx-background-color: #CB0000");
    Bmail =false ;
    }
}
    }


 public static boolean isAlpha(String s)
    {
        if (s == null) {
            return false;
        }
 
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }
 Boolean Bnom =null;
    @FXML
    private void VNom(KeyEvent event) {
         
               if (isAlpha(nom.getText().toString())) {
                nom.setStyle("-fx-background-color: #00FF51");
                Bnom =true;
                } else {
                        {nom.setStyle("-fx-background-color: #CB0000");
                         Bnom =false;
                        }
                         }
        
    }
 Boolean Bprenom =null;
    @FXML
    private void VPrenom(KeyEvent event) {
                       if (isAlpha(prenom.getText().toString())) {
    prenom.setStyle("-fx-background-color: #00FF51");
    Bprenom =true;
} else {
    {prenom.setStyle("-fx-background-color: #CB0000");
    Bprenom =false;
    }
}
    }
 public static boolean isNumerique(String s)
    {
        if (s == null) {
            return false;
        }
 
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
 Boolean Bexperience =null;
    @FXML
    private void Vexperience(KeyEvent event) {
         if (isNumerique(experience.getText().toString())) {
    experience.setStyle("-fx-background-color: #00FF51");
   Bexperience =true;
    
} else {
    {experience.setStyle("-fx-background-color: #CB0000");
       Bexperience =false;
    }
}
    }

    @FXML
    private void CorrgerBackground(MouseEvent event) {
         if(nom.getText().toString()==null || nom.getText().toString()=="")
        nom.setStyle("-fx-background-color: #FFFFFF");
    }

    @FXML
    private void CorrgerBackgroundP(MouseEvent event) {
        if(prenom.getText().toString()==null || prenom.getText().toString()=="")
        prenom.setStyle("-fx-background-color: #FFFFFF");
    }

    @FXML
    private void CorrgerBackgroundE(MouseEvent event) {
         if(experience.getText().toString()==null || experience.getText().toString()=="")
        experience.setStyle("-fx-background-color: #FFFFFF");
    }

    @FXML
    public  void AllerAccueil(MouseEvent event) {
        System.out.println("Acceuil");
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

    @FXML
    private void AnnulerBtn(MouseEvent event) {
            skills.setVisible(false);
     experience.setVisible(false);
     lexperience.setVisible(false);
     entreprise.setVisible(false);
     lentreprise.setVisible(false);
     code.setVisible(false);
     lcode.setVisible(false);
     warrning.setVisible(false);
     reussi.setVisible(false);
     erreur.setVisible(false);
     erreur2.setVisible(false);
      
     cpwd.setText("");
      cpwd.setStyle("-fx-background-color: #FFFFFF");

    email.setText("");
      email.setStyle("-fx-background-color: #FFFFFF");
    
      nom.setText("");
      nom.setStyle("-fx-background-color: #FFFFFF");
     prenom.setText("");
      prenom.setStyle("-fx-background-color: #FFFFFF");
    pwd.setText("");
      pwd.setStyle("-fx-background-color: #FFFFFF");

      
 
    }

    @FXML
    private void ValiderBTN(MouseEvent event) {
         warrning.setVisible(true);
         erreur.setVisible(false);
         erreur2.setVisible(false);
         reussi.setVisible(false);
    }

    @FXML
    private void ouiClicked(MouseEvent event) {
        ServiceUsers su = new ServiceUsers();
        ServiceRole sr = new ServiceRole();
        Integer s =su.getIdByEmail(email.getText());
         String img ;
                           if( selectedFile  != null)
                                  img= selectedFile.toURI().toString();
                           else{
                           img="img/logo.png";
                           }
        if (Bnom !=null && Bprenom !=null && Bmail !=null && Bpwd!=null)
          if (Bnom && Bprenom && Bmail && Bpwd )
            {
               if (role.getValue()=="Freelancer")
                   if( Bexperience != null && Bexperience )
                   {  
                       
                       
                       System.out.println(s);
                       if (s == -1)
                       {
                             System.out.println("erreur");
                          
                       Users u= new Users(cpwd.getText(),email.getText(), nom.getText(), prenom.getText(), Date.valueOf(LocalDate.parse(date.getValue().toString())),   img);
                       Role r = new Role("Freelancer",skills.getValue(),experience.getText(),u);
                       su.insert(u);
                       sr.insert(r);
                           erreur2.setVisible(false);
                           warrning.setVisible(false);
                           reussi.setVisible(true);
                           erreur.setVisible(false);
                       }    
                       else {  
                            erreur2.setVisible(true);
                           warrning.setVisible(false);
                           reussi.setVisible(false);
                           erreur.setVisible(false);
                       }
                   }
                   else{
                   
                     warrning.setVisible(false);
                     erreur.setVisible(true);
                   }
               else if  (role.getValue()=="Organizateur")
               {
                      if( entreprise.getText().toString() != null && entreprise.getText().toString()  !="" )
                    if (s == -1)
                       {
                             System.out.println("erreur");
                          
                       Users u= new Users(cpwd.getText(),email.getText(), nom.getText(), prenom.getText(), Date.valueOf(LocalDate.parse(date.getValue().toString())),   img);
                       Role r = new Role("Organizateur",entreprise.getText(),u);
                       su.insert(u);
                       sr.insert(r);
                           erreur2.setVisible(false);
                           warrning.setVisible(false);
                           reussi.setVisible(true);
                           erreur.setVisible(false);
                       }    
                       else {  
                            erreur2.setVisible(true);
                           warrning.setVisible(false);
                           reussi.setVisible(false);
                           erreur.setVisible(false);
                       }
                   else{
                   
                     warrning.setVisible(false);
                     erreur.setVisible(true);
                   }
               }
               
               else if  (role.getValue()=="Admin"  )
                    if( code.getText()!=null)
                    if( code.getText().equals("ADMIN" ))
                    if (s == -1)
                       {
                             System.out.println("erreur");
                          
                       Users u= new Users(cpwd.getText(),email.getText(), nom.getText(), prenom.getText(), Date.valueOf(LocalDate.parse(date.getValue().toString())),   img);
                       Role r = new Role("Admin",u);
                       su.insert(u);
                       sr.insert(r);
                           erreur2.setVisible(false);
                           warrning.setVisible(false);
                           reussi.setVisible(true);
                           erreur.setVisible(false);
                       }    
                       else {  
                            erreur2.setVisible(true);
                           warrning.setVisible(false);
                           reussi.setVisible(false);
                           erreur.setVisible(false);
                       }
                   else{
                   
                     warrning.setVisible(false);
                     erreur.setVisible(true);
                   }
                   else{
                   
                     warrning.setVisible(false);
                     erreur.setVisible(true);
                   }
                   
                
                   
               else{if (s == -1)
                       {
                             System.out.println("erreur");
                          
                       Users u= new Users(cpwd.getText(),email.getText(), nom.getText(), prenom.getText(), Date.valueOf(LocalDate.parse(date.getValue().toString())),   img);
                       Role r = new Role("Client",u);
                       su.insert(u);
                       sr.insert(r);
                           erreur2.setVisible(false);
                           warrning.setVisible(false);
                           reussi.setVisible(true);
                           erreur.setVisible(false);
                       }    
                       else {  
                            erreur2.setVisible(true);
                           warrning.setVisible(false);
                           reussi.setVisible(false);
                           erreur.setVisible(false);
                       }} 
     
            }
           else { 
          warrning.setVisible(false);
    
          erreur.setVisible(true);
            }
        else { erreur.setVisible(true); }
    }

    @FXML
    private void nonClicked(MouseEvent event) {
        warrning.setVisible(false);
    }

    @FXML
    private void signin(MouseEvent event) {
         ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Connecter.fxml"));
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
        
       
        }
    }

   

   









}
