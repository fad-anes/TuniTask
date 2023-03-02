package gui;

import entite.Role;
import entite.Users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Random;


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
    private TextArea Langage;
    @FXML
    private Pane PaneDate;
    @FXML
    private Label Llangae;
                        String coder;

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
     Llangae.setVisible(false);
     Langage.setVisible(false);
    break;
  case "Freelancer":
    skills.setVisible(true);
     experience.setVisible(true);
     lexperience.setVisible(true);
     entreprise.setVisible(false);
     lentreprise.setVisible(false);
      code.setVisible(false);
     lcode.setVisible(false);
     Llangae.setVisible(true);
     Langage.setVisible(true);
    break;
    case "Organizateur":
   skills.setVisible(false);
     experience.setVisible(false);
     lexperience.setVisible(false);
     entreprise.setVisible(true);
     lentreprise.setVisible(true);
      code.setVisible(false);
     lcode.setVisible(false);
     Llangae.setVisible(false);
     Langage.setVisible(false);
      
    break;
  default:
     skills.setVisible(false);
     experience.setVisible(false);
     lexperience.setVisible(false);
     entreprise.setVisible(false);
     lentreprise.setVisible(false);
     code.setVisible(true);
     lcode.setVisible(true);
     Llangae.setVisible(false);
     Langage.setVisible(false);
                    coder = generateRandomCode();
                    UserSession usersess= UserSession.getInstance();
                   usersess.setCode(coder);
                   EnvoyerEmail test = new EnvoyerEmail();
                   test.envoyer("abdessalam.bahri@esprit.tn");
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
     PaneDate.setVisible(false);
     Llangae.setVisible(false);
     Langage.setVisible(false);
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
        nom.setStyle("-fx-background-color:  #77119B");
    }

    @FXML
    private void CorrgerBackgroundP(MouseEvent event) {
        if(prenom.getText().toString()==null || prenom.getText().toString()=="")
        prenom.setStyle("-fx-background-color:  #77119B");
    }

    @FXML
    private void CorrgerBackgroundE(MouseEvent event) {
         if(experience.getText().toString()==null || experience.getText().toString()=="")
        experience.setStyle("-fx-background-color:  #77119B");
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
      date.setValue(null);
     cpwd.setText("");
      cpwd.setStyle("-fx-background-color:  #77119B");

    email.setText("");
      email.setStyle("-fx-background-color:  #77119B");
    Langage.setText("");
    Langage.setVisible(false);
    Llangae.setVisible(false);
      
      
      nom.setText("");
      nom.setStyle("-fx-background-color:  #77119B");
     prenom.setText("");
      prenom.setStyle("-fx-background-color:  #77119B");
    pwd.setText("");
      pwd.setStyle("-fx-background-color:  #77119B");
Image image = new Image("C:/Users/abdes/OneDrive/Documents/NetBeansProjects/TuniTask/src/gui/img/logo.png");
        IMAGE.setImage(image);
      
 
    }

    @FXML
    private void ValiderBTN(MouseEvent event) {
         warrning.setVisible(true);
         erreur.setVisible(false);
         erreur2.setVisible(false);
         reussi.setVisible(false);
    }
private static final int CODE_LENGTH = 6;
 public static String generateRandomCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            codeBuilder.append(digit);
        }
        return codeBuilder.toString();
    }
    @FXML
    private void ouiClicked(MouseEvent event) {
      
        ServiceUsers su = new ServiceUsers();
        ServiceRole sr = new ServiceRole();
        System.out.println(date.getValue());
        Integer s =su.getIdByEmail(email.getText());
         String img ;
                           if( selectedFile  != null)
                                  img= selectedFile.toURI().toString();
                               
                           else{
                           img="C:/Users/abdes/OneDrive/Documents/NetBeansProjects/TuniTask/src/gui/img/logo.png";
                           }
                           
        if (Bnom !=null && Bprenom !=null && Bmail !=null && Bpwd!=null)
          if (Bnom && Bprenom && Bmail && Bpwd && Vdate )
            {
               if (role.getValue()=="Freelancer")
                   if( Bexperience != null && Bexperience )
                   {  
                       
                       
                       System.out.println(s);
                       if (s == -1)
                       {
                             System.out.println("erreur");
                          //String hash=hashPassword(cpwd.getText());
                       Users u= new Users(cpwd.getText(),email.getText(), nom.getText(), prenom.getText(), Date.valueOf(LocalDate.parse(date.getValue().toString())),   img);
                       Role r = new Role("Freelancer",skills.getValue(),experience.getText(),u,Langage.getText());
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
               
               else if  (role.getValue()=="Admin"  ){
                    if( code.getText()!=null)
                    if( code.getText().equals(coder ))
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
                   
                
                   
               } else{if (s == -1)
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
        /*Path sourcePath = Paths.get(selectedFile.toURI().toString());
        Path destinationPath = Paths.get("C/xampp/htdocs/img");
          try {
            // Utiliser la méthode Files.copy pour copier l'image
            Files.copy(sourcePath, destinationPath);
            
            System.out.println("L'image a été copiée avec succès.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /* File sourceFile = selectedFile;
                       File targetDirectory = new File("C:/xampp/htdocs/img");
                       String imgsrc = "Test" +".png"; 
                       File destinationFile = new File(targetDirectory,imgsrc  );
                       try {
                        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                         } catch (IOException e) {
                   e.printStackTrace();
                    } */
        /*String sourcePath=selectedFile.toURI().toString();
            System.out.println(sourcePath);
        String destinationPath = "C:/xampp/htdocs/img/email.png";
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(sourcePath));
            
            // Ouvrir un flux de sortie pour écrire l'image dans le nouveau dossier
            FileOutputStream fileOutputStream = new FileOutputStream(new File(destinationPath));
            
            // Créer un tampon pour stocker les données lues à partir de l'entrée
            byte[] buffer = new byte[1024];
            int length;
            
            // Lire les données de l'entrée et les écrire dans le nouveau dossier
            while ((length = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            
            // Fermer les flux de lecture et d'écriture
            fileInputStream.close();
            fileOutputStream.close();
            
            System.out.println("L'image a été copiée avec succès.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
       */
        }
    }

    Boolean Vdate=false;
    @FXML
    private void VerifierDate(MouseEvent event) {
        if ( date.getValue() != null )
        {
        System.out.println(LocalDate.now());
        System.out.println(date.getValue());
        
        LocalDate date1 = date.getValue();
        LocalDate currentDate = LocalDate.now();
        
        if (ChronoUnit.YEARS.between(date1, currentDate)< 18)
        {erreur.setVisible(true);
           Vdate=false;}
        else
        {erreur.setVisible(false);
          Vdate=true;
        }
                
                
        }
        else {PaneDate.setVisible(true); 
              erreur.setVisible(false);
              Vdate=true;
        }
    }

    @FXML
    private void AnnuleDate(MouseEvent event) {
         PaneDate.setVisible(false); 
          if ( date.getValue() != null )
        {
        System.out.println(LocalDate.now());
        System.out.println(date.getValue());
        
        LocalDate date1 = date.getValue();
        LocalDate currentDate = LocalDate.now();
        
        if (ChronoUnit.YEARS.between(date1, currentDate)< 18)
        {erreur.setVisible(true);
           Vdate=false;}
        else
        {erreur.setVisible(false);}}
          else {
           erreur.setVisible(false);
           
          }
         
         
    }

    @FXML
    private void getDateValue(MouseEvent event) {
    }


  
   


   
    

  
   

   









}
