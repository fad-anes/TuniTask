/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Role;
import entite.Users;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import service.ServiceRole;
import service.ServiceUsers;

/**
 * FXML Controller class
 *
 * @author abdes
 */
public class CompteController implements Initializable {

     @FXML
    private Label Date;

    @FXML
    private Label IDacceuil;

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
    private ImageView image;

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
    private Button signupI;

    @FXML
    private Pane warrning;
        @FXML
    private TextField passwordA;

   
    ServiceUsers u ;
    ServiceRole r ;
    @FXML
    private Label lnom;
    @FXML
    private Label lemail;
    @FXML
    private Label lprenom;
    @FXML
    private ImageView Image;
    @FXML
    private Pane PaneValid;
    private Label changer1;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        u=new ServiceUsers();
        r=new  ServiceRole();
       PaneValid.setVisible(false);
       warrning1.setVisible(false);
       warrning.setVisible(false);
       erreur.setVisible(false);
       reussi.setVisible(false);
       Users  user = u.readById(UserSession.getInstance().getId().intValue());
       Role role= r.Role_By_Id_user(user.getId());
       Image image = new Image(user.getSrcimage());
       
       
       Image.setImage(image);
       nom.setText(user.getFirstName());
       prenom.setText(user.getLastName());
       date.setValue(LocalDate.parse(user.getDateOfBirth().toString()));
       email.setText(user.getEmail());
       passwordA.setText(user.getPassword());
       passwordA.setVisible(false);
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
    void ajusterLayout(int x)
    {  lnom.setLayoutY(lnom.getLayoutY() + x);
         lprenom.setLayoutY(lprenom.getLayoutY() + x);
         lemail.setLayoutY(lemail.getLayoutY() + x);
         nom.setLayoutY(nom.getLayoutY() + x);
         prenom.setLayoutY(prenom.getLayoutY() + x);
         email.setLayoutY(email.getLayoutY() + x);
         Date.setLayoutY(Date.getLayoutY() + x);
         date.setLayoutY(date.getLayoutY() + x);
         changernom.setLayoutY(changernom.getLayoutY() + x);
         changerprenom.setLayoutY(changerprenom.getLayoutY() + x);
         changeremail.setLayoutY(changeremail.getLayoutY() + x);
         changeredate.setLayoutY(changeredate.getLayoutY() + x);
    }

    @FXML
    private void signin(MouseEvent event) {
    }

    @FXML
    private void AllerAccueil(MouseEvent event) {
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
       
    }

   
    void ModifierBTN(MouseEvent event) {

    }

    @FXML
    private void ouiClicked(MouseEvent event) {
   Users us= new  Users( 12,"test",email.getText(),nom.getText(),prenom.getText(), java.sql.Date.valueOf(date.getValue().toString()),"src");
        
    }

    @FXML
    private void nonClicked(MouseEvent event) {
        
    }

    @FXML
    private void changerNom(MouseEvent event) {
       PaneValid.setVisible(true);
       nom.setDisable(false);
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

    }

    @FXML
    private void changerEmail(MouseEvent event) {
            email.setDisable(false);
            PaneValid.setVisible(true);
            warrning.setVisible(false);
    }

    @FXML
    private void changerDate(MouseEvent event) {
        date.setDisable(false);
        PaneValid.setVisible(true);
    }

    @FXML
    private void changerEntreprise(MouseEvent event) {
        entreprise.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
        
    }

    @FXML
    private void changerCompetence(MouseEvent event) {
        competence.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
    }

    @FXML
    private void changereAnne(MouseEvent event) {
        annee.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
    }

    @FXML
    private void changerLangage(MouseEvent event) {
        langage.setDisable(false);
        PaneValid.setVisible(true);
        warrning.setVisible(false);
    }

    @FXML
    private void ValiderBTN(MouseEvent event) {
        PaneValid.setVisible(false);
        warrning.setVisible(true);
    }

   
    
}
