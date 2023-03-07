/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Role;
import entite.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ServiceRole;
import service.ServiceUsers;

/**
 * FXML Controller class
 *
 * @author abdes
 */
public class AdminController implements Initializable {

    @FXML
    private Button valid;
    @FXML
    private Button annule;
    @FXML
    private Button Myprofile;
    @FXML
    private Button logout;
    @FXML
    private Label Clients;
    @FXML
    private Label Freelancers;
    @FXML
    private Label Organizateurs;
    @FXML
    private Label Admins;
    @FXML
    private TableView<Users> Table;
    @FXML
    private Button Desactive;
    @FXML
    private TextField donnee;
    @FXML
    private TableColumn<Users, Integer> idcolum;
    @FXML
    private TableColumn<Users, String> email;
    @FXML
    private TableColumn<Users, String> nom;
    @FXML
    private TableColumn<Users, String> prenom;
    @FXML
    private TableColumn<Users, Date> Dn;
    @FXML
    private TableColumn<Users, Date> Dc;
    @FXML
    private ImageView imageuser;
    @FXML
    private TextField commentaire;
    @FXML
    private Pane PaneValide;
    @FXML
    private ComboBox<String> Filter;
   ServiceUsers su =null;
    
    @FXML
    private TableColumn<Users, Date> statut;
    @FXML
    private Button activer;
    @FXML
    private Pane reussi;
    @FXML
    private ImageView image1;
    @FXML
    private Label User;
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

    /**
     * Initializes the controller class.
     */

  ObservableList<Users> filtrer ( ArrayList <Users> users)
    {
      
      idcolum.setCellValueFactory(new PropertyValueFactory<>("id"));
      email.setCellValueFactory(new PropertyValueFactory<>("email"));
      nom.setCellValueFactory(new PropertyValueFactory<>("firstName"));
      prenom.setCellValueFactory(new PropertyValueFactory<>("lastName"));
      Dn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
      Dc.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
      statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
      ObservableList<Users> userList = FXCollections.observableArrayList();
      users.forEach((Users u) -> userList.add(u));
      return userList ;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        activer.setVisible(false);
        
        reussi.setVisible(false);
         su = new ServiceUsers () ;
     Clients.setStyle("-fx-border-color:  #9EFF00");
      ArrayList <Users> users= (ArrayList ) su.filtrer_users_By_Role("Client");
      ObservableList<Users> userList= filtrer (users);
      Table.setItems(userList);
      commentaire.setVisible(false);
      PaneValide.setVisible(false);
      Desactive.setVisible(false);
      idcolum.setStyle("-fx-background-color:  #DAC0FF;");
      email.setStyle("-fx-background-color:  #EBCBF6;");
       nom.setStyle("-fx-background-color:  #DAC0FF;");
      prenom.setStyle("-fx-background-color:   #EBCBF6;");
      Dn.setStyle("-fx-background-color:  #DAC0FF;");
      Dc.setStyle("-fx-background-color:   #EBCBF6;");
      statut.setStyle("-fx-background-color:   #DAC0FF;");
      Filter.setItems(FXCollections.observableArrayList("Id","Nom","Prenom","Email","Date de naissance","Date de création","statut"));
      
      
       
    }    
   

   Users userselected ;
      String Filter_Role="Client" ;
    @FXML
    private void Prendre_ligne(MouseEvent event) {
         
        PaneValide.setVisible(false);
        Table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,newValue) -> {
            reussi.setVisible(false);
    if (newValue != null) {
        userselected =newValue;
         
         if (newValue.getStatut()==true)
        {Desactive.setVisible(true);
        activer.setVisible(false);}
        else{
            Desactive.setVisible(false);
        activer.setVisible(true);
        }
         ServiceRole r = new ServiceRole();
         Role role =r.Role_By_Id_user(newValue.getId());
          if(role.getRoleName().equals("Admin"))
                {
                      
                 Desactive.setVisible(false);
                 activer.setVisible(false);
                }
        System.out.println("Id : " + newValue.getId());
         Image image = new Image("file:///"+newValue.getSrcimage());
         
         
         System.out.println(role.getRoleName());
        
        
         //commentaire.setText("travaille à \t" + role.getEntreprise() );
          if(role.getRoleName().equals("Freelancer"))
                { 
                    commentaire.setText("Développeur "+ role.getSkills()+ " depuis "+
                 role.getExperience() +" ans " );
                    commentaire.setVisible(true);
                }
                else{ 
                  if( role.getRoleName().equals("Organizateur"))
                {commentaire.setText("travaille à "+ role.getEntreprise());
                    commentaire.setVisible(true);}
                else{commentaire.setVisible(false);}
           }
       imageuser.setImage(image);
    }
});
    }

    @FXML
    private void Clients(MouseEvent event) {
        Filter_Role="Client";
        System.out.println(UserSession.getInstance().getId());
        activer.setVisible(false);
        PaneValide.setVisible(false);
      Desactive.setVisible(false);
         commentaire.setVisible(false);
          Clients.setStyle("-fx-border-color:   #9EFF00");
         Organizateurs.setStyle("-fx-border-color:  #77119B");
         Admins.setStyle("-fx-border-color:   #77119B");
         Freelancers.setStyle("-fx-border-color:  #77119B");
         
         ArrayList <Users> users= (ArrayList ) su.filtrer_users_By_Role("Client");
      ObservableList<Users> userList= filtrer (users);
      Table.setItems(userList);
    }

    @FXML
    private void Freelancers(MouseEvent event) {
         Filter_Role="Freelancer";
        PaneValide.setVisible(false);
      Desactive.setVisible(false);
         commentaire.setVisible(false);
         activer.setVisible(false);
         Clients.setStyle("-fx-border-color:   #77119B");
         Organizateurs.setStyle("-fx-border-color:  #77119B");
         Admins.setStyle("-fx-border-color:   #77119B");
         Freelancers.setStyle("-fx-border-color:  #9EFF00");
      ArrayList <Users> users= (ArrayList ) su.filtrer_users_By_Role("Freelancer");
      ObservableList<Users> userList= filtrer (users);
      Table.setItems(userList);
    }

    @FXML
    private void Organizateurs(MouseEvent event) {
        Filter_Role="Organizateur";
        PaneValide.setVisible(false);
      Desactive.setVisible(false);
         commentaire.setVisible(false);
         activer.setVisible(false);
        Clients.setStyle("-fx-border-color:   #77119B");
         Organizateurs.setStyle("-fx-border-color:  #9EFF00");
         Admins.setStyle("-fx-border-color:   #77119B");
         Freelancers.setStyle("-fx-border-color:  #77119B");
     ArrayList <Users> users= (ArrayList ) su.filtrer_users_By_Role("Organizateur");
      ObservableList<Users> userList= filtrer (users);
      Table.setItems(userList);
    }

    @FXML
    private void Admins(MouseEvent event) {
        Filter_Role="Admin";
        PaneValide.setVisible(false);
      Desactive.setVisible(false);
      activer.setVisible(false);
         commentaire.setVisible(false);
        Clients.setStyle("-fx-border-color:   #77119B");
         Organizateurs.setStyle("-fx-border-color:  #77119B");
         Admins.setStyle("-fx-border-color:   #9EFF00");
         Freelancers.setStyle("-fx-border-color:  #77119B");
     ArrayList <Users> users= (ArrayList ) su.filtrer_users_By_Role("Admin");
      ObservableList<Users> userList= filtrer (users);
      Table.setItems(userList);
    }

  


   String getFilter ;
   String column ;
    @FXML 
    private void initialize(ActionEvent event) {
        
              System.out.println(Filter.getValue());
        switch(Filter.getValue()) {
  case "Nom":
      donnee.setPromptText("Entrez un nom");
     
    
    break;
  case "Prenom":
    donnee.setPromptText("Entrez un Prenom");
   
    
    break;
    case "Id":
    donnee.setPromptText("Entrez un nombre");
   
      
    break;
    case "Email":
   donnee.setPromptText("email");

      
    break;
    case "Date de naissance":
    donnee.setPromptText("Entrez Date de naissance");

    break;
    
  default:
       
      donnee.setPromptText("0 = non activé ou 1 = activé ");
        }
       
    }
   /*"Nom","Prenom","Email","Admin","Date de naissance","Date de création"*/
   
    @FXML
    private void FiltrerKeyReleased(KeyEvent event) {
         try{
        ArrayList <Users> list1=null;
        ServiceUsers su= new ServiceUsers();
       ArrayList <Users> list=(ArrayList)su.filtrer_users_By_Role(Filter_Role);
      
       if (Filter.getValue()=="Nom")
       {
           donnee.setVisible(true);
       
           list1=(ArrayList)list.stream().filter((Users u) -> u.getFirstName().length()>= donnee.getText().length())
                .filter(u -> u.getFirstName().substring(0,donnee.getText().length()).equals(donnee.getText()) )
                .collect(Collectors.toList());
       }
       else if (Filter.getValue()=="Prenom")
       {
           donnee.setVisible(true);
       
           list1=(ArrayList)list.stream().filter((Users u) -> u.getLastName().length()>= donnee.getText().length())
                .filter(u -> u.getLastName().substring(0,donnee.getText().length()).equals(donnee.getText()) )
                .collect(Collectors.toList());   
       }
       else if (Filter.getValue()=="Email")
       {
      
           list1=(ArrayList)list.stream().filter((Users u) -> u.getEmail().length()>= donnee.getText().length())
                .filter(u -> u.getEmail().substring(0,donnee.getText().length()).equals(donnee.getText()) )
                .collect(Collectors.toList());   
       }
       else if(Filter.getValue()=="Id"){
           donnee.setVisible(true);
       
        list1=(ArrayList)list.stream().filter((Users u) ->{
                                           System.out.println(u.getId());
                                           System.out.println(donnee.getText().length());
                                           System.out.println(Integer.toString(u.getId()).length());
                                    return Integer.toString(u.getId()).length()>= donnee.getText().length();})
                .filter(u -> u.getId()==Integer.parseInt(donnee.getText()) )
                .collect(Collectors.toList());
       }
       else if (Filter.getValue().equals("Date de naissance"))
            {
              
                list1=(ArrayList)list.stream()
                .filter((Users u) -> {
                    System.out.println(u.getDateOfBirth().toString().length());
                    System.out.println(donnee.getText().toString().length());
                   return u.getDateOfBirth().toString().length()>= donnee.getText().length();
                
                                           })
                .filter(u -> u.getDateOfBirth().toString().substring(0,donnee.getText().length()).equals(donnee.getText().toString()))
                .collect(Collectors.toList());}
              else  if (Filter.getValue().equals("statut"))
            {
               
                list1=(ArrayList)list.stream()
                .filter((Users u) -> {
                    System.out.println(donnee.getText());
                    if ( Integer.parseInt(donnee.getText())==1)
                    {
                        System.out.println(true);
                        return u.getStatut()==true;
                        
                    }
                               else
                    {return u.getStatut()==false;}})
                    
                .collect(Collectors.toList());
                 
            }
              else{
                 list1=(ArrayList)list.stream()
           
                .filter(u -> u.getCreatedAt().toString().substring(0,donnee.getText().length()).equals(donnee.getText().toString()))
                .collect(Collectors.toList());
              }
   
      ObservableList<Users> userList = FXCollections.observableArrayList();
    
        list1.forEach((Users u) -> userList.add(u));
        Table.setItems(userList);
    }
    catch( Exception e)
    {
        System.out.println("empty"); 
    }
    }

    @FXML
    private void Profile(ActionEvent event) {
        ( (Node) event.getSource()).getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Compte.fxml"));
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
    private void activer(ActionEvent event) {
           Desactive.setVisible(false);
           activer.setVisible(false);
        PaneValide.setVisible(true);
    }

    @FXML
    private void desacitiver(ActionEvent event) {
        activer.setVisible(false);
        Desactive.setVisible(false);
        PaneValide.setVisible(true);
    }

    @FXML
    private void ValiderClicked(MouseEvent event) {
         activer.setVisible(false);
         Desactive.setVisible(false);
        PaneValide.setVisible(false);
        su.Desactiver(userselected);
        reussi.setVisible(true);
       
    }

    @FXML
    private void User(MouseEvent event) {
        
    }

    @FXML
    private void Demande(MouseEvent event) {
    }

    @FXML
    private void evénement(MouseEvent event) {
    }

    @FXML
    private void quiz(MouseEvent event) {
    }

    @FXML
    private void reclamation(MouseEvent event) {
    }

    @FXML
    private void offre(MouseEvent event) {
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

   

   
    @FXML
    private void AnnuleClicked(MouseEvent event) {
        PaneValide.setVisible(false);
    }

    @FXML
    private void signup(ActionEvent event) {
    }

    
    
}