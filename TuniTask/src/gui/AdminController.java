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
    private ImageView image;
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

    /**
     * Initializes the controller class.
     */

 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
      Filter.setItems(FXCollections.observableArrayList("Nom","Prenom","Email","Admin","Date de naissance","Date de création"));
      
      
       
    }    
    ObservableList<Users> filtrer ( ArrayList <Users> users)
    {
      
      idcolum.setCellValueFactory(new PropertyValueFactory<>("id"));
      email.setCellValueFactory(new PropertyValueFactory<>("email"));
      nom.setCellValueFactory(new PropertyValueFactory<>("firstName"));
      prenom.setCellValueFactory(new PropertyValueFactory<>("lastName"));
      Dn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
      Dc.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
      ObservableList<Users> userList = FXCollections.observableArrayList();
      
      users.forEach((Users u) -> userList.add(u));
      return userList ;
    }
    @FXML
    private void signup(ActionEvent event) {
    }


    @FXML
    private void ValideClicked(ActionEvent event) {
    }

    @FXML
    private void AnnuleClicked(MouseEvent event) {
    }

    @FXML
    private void Prendre_ligne(MouseEvent event) {
        
        Table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,newValue) -> {
    if (newValue != null) {
        Desactive.setVisible(true);
        System.out.println("Id : " + newValue.getId());
         Image image = new Image(newValue.getSrcimage());
         ServiceRole r = new ServiceRole();
         Role role =r.Role_By_Id_user(newValue.getId());
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
        System.out.println(UserSession.getInstance().getId());
        
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
        PaneValide.setVisible(false);
      Desactive.setVisible(false);
         commentaire.setVisible(false);
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
        PaneValide.setVisible(false);
      Desactive.setVisible(false);
         commentaire.setVisible(false);
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
        PaneValide.setVisible(false);
      Desactive.setVisible(false);
         commentaire.setVisible(false);
        Clients.setStyle("-fx-border-color:   #77119B");
         Organizateurs.setStyle("-fx-border-color:  #77119B");
         Admins.setStyle("-fx-border-color:   #9EFF00");
         Freelancers.setStyle("-fx-border-color:  #77119B");
     ArrayList <Users> users= (ArrayList ) su.filtrer_users_By_Role("Admin");
      ObservableList<Users> userList= filtrer (users);
      Table.setItems(userList);
    }

    @FXML
    private void desacitiver(ActionEvent event) {
        Desactive.setVisible(false);
        PaneValide.setVisible(true);
    }


   String getFilter ;
    @FXML 
    private void initialize(ActionEvent event) {
        
              System.out.println(Filter.getValue());
        switch(Filter.getValue()) {
  case "Nom":
      donnee.setPromptText("Entrez un nom");
       getFilter="Nom";
       
       
    break;
  case "Freelancer":
    
    break;
    case "Organizateur":
  
      
    break;
  default:
        }
    }
   
    @FXML
    private void FiltrerKeyReleased(KeyEvent event) {
        ServiceUsers su= new ServiceUsers();
       ArrayList <Users> list=(ArrayList)su.filtrer_users_By_Role("Client");
        ArrayList <Users> list1=(ArrayList)list.stream().filter((Users u) -> u.getFirstName().length()>= donnee.getText().length())
                .filter(u -> u.getFirstName().substring(0,donnee.getText().length()).equals(donnee.getText()) )
                .collect(Collectors.toList());
         
      ObservableList<Users> userList = FXCollections.observableArrayList();
        list1.forEach((Users u) -> userList.add(u));
        Table.setItems(userList);
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
    
    
}