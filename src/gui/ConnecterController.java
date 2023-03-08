/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.PasswordHasher;
import entite.Users;
import static gui.InscrireController.generateRandomCode;
import static gui.InscrireController.verfierEmail;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ServiceRole;
import service.ServiceUsers;

/**
 * FXML Controller class
 *
 * @author abdes
 */
public class ConnecterController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label IDacceuil;
    @FXML
    private TextArea email;
    @FXML
    private PasswordField cpwd;
    @FXML
    private Button annule;
    @FXML
    private Button valid;
    @FXML
    private Pane erreur_donnée;
    @FXML
    private Pane warrning;
    @FXML
    private Button ouiBTN;
    @FXML
    private Button non;
    boolean Bmail;
    @FXML
    private Pane erreur_donnée1;
    @FXML
    private Button signup;
    ServiceRole ru = null;
    @FXML
    private Label oublie;
    @FXML
    private Pane Compte_des;
    @FXML
    private Pane Paneverification;
    @FXML
    private TextField codeV;
    @FXML
    private Pane PanePWD;
    @FXML
    private TextField NpasswordA;
    @FXML
    private TextField NpasswordAv;
    @FXML
    private Label lpassword;
    @FXML
    private Label lcpwd;
    @FXML
    private Pane reussi;
    @FXML
    private void signup(ActionEvent event) {
          ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Inscrire.fxml"));
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
    private void AllerAccueil(MouseEvent event) {
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

    @FXML
    private void AnnuleClicked(MouseEvent event) {
        erreur_donnée.setVisible(false);
        warrning.setVisible(false);
         erreur_donnée1.setVisible(false);
         cpwd.setText("");
         email.setText("");
          cpwd.setStyle("-fx-background-color: #FFFFFF");
          email.setBackground(Background.EMPTY);
           oublie.setVisible(false);
           Paneverification.setVisible(false);
           PanePWD.setVisible(true);
           
    }

    @FXML
    private void ValideClicked(ActionEvent event) {
            if (!Bmail)
        //To change body of generated methods, choose Tools | Templates.
            { erreur_donnée.setVisible(true);
        warrning.setVisible(false);
             erreur_donnée1.setVisible(false);
             Compte_des.setVisible(false);
             oublie.setVisible(false);
             reussi.setVisible(false);
             NpasswordA.setText("");
                      NpasswordAv.setText("");
                      codeV.setText("");
            }
        else{
             erreur_donnée.setVisible(false);
              erreur_donnée1.setVisible(false);
        warrning.setVisible(true); 
         Compte_des.setVisible(false);
         oublie.setVisible(false);
         reussi.setVisible(false);
          NpasswordA.setText("");
          NpasswordAv.setText("");
          codeV.setText("");
                }
        
    }
ServiceUsers su;
    @FXML
    private void ouiClicked(MouseEvent event) {
         su = new ServiceUsers();
        if(su.getIdByEmail(email.getText())==-1)
        {
            erreur_donnée.setVisible(false);
              erreur_donnée1.setVisible(true);
        warrning.setVisible(false);
         Compte_des.setVisible(false);
        }
        else {  
            //System.out.println(su.getPwdByEmail(email.getText()).equals(cpwd.getText()));
            // System.out.println(cpwd.getText());
             PasswordHasher ph=new PasswordHasher();
        if(su.getPwdByEmail(email.getText()).equals(ph.hashPassword(cpwd.getText()))==false)
        {
            System.out.println("okbb");
            Compte_des.setVisible(false);
             oublie.setVisible(true);
         erreur_donnée.setVisible(true);
              erreur_donnée1.setVisible(false);
        warrning.setVisible(false);
       
        }
        else {
          
           
            ru= new ServiceRole();
            int id = su.getIdByEmail(email.getText());
             UserSession usersess= UserSession.getInstance();
             usersess.setId(Long.valueOf(id));
            System.out.println(id);
            if(ru.Role_By_Id_user(id).getRoleName().equals("Admin")){
            System.out.println("Admin");
                UserSession.getInstance().setRole("Admin");
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
            else if (su.getStatutByEmail(email.getText()))
            {System.out.println("simpleuser");
            if (ru.Role_By_Id_user(id).getRoleName().equals("Organizateur"))
            {System.out.println("Organizateur");
                UserSession.getInstance().setRole("Organizateur");
            }
            else if (ru.Role_By_Id_user(id).getRoleName().equals("Client"))
            {System.out.println("Client");
                UserSession.getInstance().setRole("Client");
                {( (Node) event.getSource()).getScene().getWindow().hide();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("client.fxml"));
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
                    }}}
            else if (ru.Role_By_Id_user(id).getRoleName().equals("Freelancer"))
            {
                UserSession.getInstance().setRole("Freelancer");
                ( (Node) event.getSource()).getScene().getWindow().hide();
                Parent root;
                try {
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
                }}

            }
            else{
                         Compte_des.setVisible(true);
             oublie.setVisible(false);
         erreur_donnée.setVisible(false);
              erreur_donnée1.setVisible(false);
        warrning.setVisible(false);
            }    //System.out.println("Login"); 
       }
        }
    }

    @FXML
    private void nonClicked(MouseEvent event) {
        warrning.setVisible(false);
         oublie.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Compte_des.setVisible(false);
        //To change body of generated methods, choose Tools | Templates.
        erreur_donnée.setVisible(false);
        warrning.setVisible(false);
         erreur_donnée1.setVisible(false);
         oublie.setVisible(false);
         Paneverification.setVisible(false);
         reussi.setVisible(false);
         PanePWD.setVisible(false);
        
    }
     String coder;
    @FXML
    private void Motoublie(MouseEvent event) {
        
        oublie.setVisible(false);
        erreur_donnée.setVisible(false);
        Paneverification.setVisible(true);
        PanePWD.setVisible(false);
                   UserSession usersess= UserSession.getInstance();
                   coder = generateRandomCode();
                   System.out.println("");
                   usersess.setCode(coder);
                   EnvoyerEmail test = new EnvoyerEmail();
                   //test.envoyer("abdessalam.bahri@isticbc.org");
                   test.envoyer(email.getText());
        
    }

    @FXML
    private void cacherWarrnning(KeyEvent event) {
        warrning.setVisible(false);
    }

    @FXML
    private void CodedeVerificationEntred(KeyEvent event) {
         if(codeV.getText().equals(coder ))
                          {
                              codeV.setStyle("-fx-background-color: #00FF51");
                              Paneverification.setVisible(true);
                              PanePWD.setVisible(true);
                          }
         else
         {codeV.setStyle("-fx-background-color: #CB0000");}
        
    }

    @FXML
    private void Passwordchanged(KeyEvent event) {
        if( NpasswordA.getText().equals(NpasswordAv.getText()))
        {  PasswordHasher ph=new PasswordHasher();
            su=new ServiceUsers();
            Users u = su.readById(su.getIdByEmail(email.getText()));
            u.setPassword(ph.hashPassword(NpasswordAv.getText()));
            su.update(u);
            PanePWD.setVisible(false);
            reussi.setVisible(true);
            Paneverification.setVisible(false);
        }
        
    }

   
    
}
