/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import Services.ServiceReponserec;
import cnx_DB.DataSource;
import entity.Reclamation;
import entity.Reponserec;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class TableReclamationController implements Initializable {
    
    @FXML
    private TableView<Reclamation> tableReclamation;

    @FXML
    private TextField textid;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtdescription;
    @FXML
    private Label libEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btndelete;
    @FXML
    private TextField txtresolution;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete1;
    @FXML
    private TableColumn<Reclamation, Integer> idcl;
    @FXML
    private TableColumn<Reclamation, String> nomcl;
    @FXML
    private TableColumn<Reclamation, String> prenomcl;
    @FXML
    private TableColumn<Reclamation, String> emailcl;
    @FXML
    private TableColumn<Reclamation, String> descriptioncl;
   
         Stage stage;
    Connection mc;
    Connection mc2;
    PreparedStatement ste;
       PreparedStatement ste2;
     ObservableList<Reclamation>recList;
    ServiceReponserec rpc = new ServiceReponserec();
     public ObservableList<Reclamation> list;
    @FXML
    private Button btndelete11;
    @FXML
    private TextField inputRech;
    @FXML
    private Button afficherparno;
    @FXML
    private ComboBox<Integer> comboboxuser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherReclamation();
    }  
    
    void afficherReclamation(){
        
            mc=DataSource.getInstance().getCnx();
        recList = FXCollections.observableArrayList();
       
         try {
            String req = "select * from users";
            Statement stm = mc.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
                
                Integer xx = rst.getInt("id");
                comboboxuser.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
     
        String sql="SELECT reclamation.* FROM reclamation LEFT JOIN reponserec ON reclamation.id = reponserec.id WHERE reponserec.id IS NULL;";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
         
            
            while((rs.next()  )){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("reclamation.id"));
                r.setNom(rs.getString("reclamation.nom"));
                r.setPrenom(rs.getString("reclamation.prenom"));
                r.setEmail(rs.getString("reclamation.email"));
                r.setDescription(rs.getString("reclamation.description"));
               
             
             
                recList.add(r); 
                
                
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
         
      
        idcl.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getId()));
        nomcl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenomcl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        emailcl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        descriptioncl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
      
           tableReclamation.setItems(recList);
       
      try {
          
          ServiceReclamation ss = new ServiceReclamation();
            list = FXCollections.observableArrayList(
                    ss.afficherReclamations()
            );        
        
        
   FilteredList<Reclamation> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Reclamation>) Reclamations -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        String tostring = Reclamations.getNom();
                        if (tostring.toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableReclamation.comparatorProperty());
                tableReclamation.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    
        
    }
       public void resetTableData() throws SQLDataException, SQLException {
        ServiceReclamation cs = new ServiceReclamation();
        List<Reclamation> listrec = new ArrayList<>();
        listrec = cs.afficherReclamations2();
        ObservableList<Reclamation> data = FXCollections.observableArrayList(listrec);
        tableReclamation.setItems(data);
    }
    @FXML
    private void addResolution(ActionEvent event) {
        String id = textid.getText();
        String nomAg = "omrani";
        String prenomAg = "hadhemi"; // bch te5ou text mawjoud f label w thotou f variable
        String resolution = txtresolution.getText();
        
     
          
             
             Reponserec r=new Reponserec(Integer.valueOf(id),nomAg,prenomAg,resolution);
             ServiceReponserec rc = new ServiceReponserec(); 
                        Alert alert4 = new Alert(Alert.AlertType.CONFIRMATION);
      alert4.setTitle("Confirmer ");
      alert4.setHeaderText("Confirmer");
      alert4.setContentText(" ");
             rc.ajouterReponserec(r);
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Resolution Ajoutée avec succes!");
                alert.showAndWait();
                
      
          Videtable();
         
         afficherReclamation();
         
                             TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouté avec succés");
            tray.setMessage("Ajouté avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
         
    }
    
    @FXML
    void getSelected(MouseEvent event) {
      Reclamation clicked = tableReclamation.getSelectionModel().getSelectedItem();
          textid.setText(String.valueOf(clicked.getId()));
         txtnom.setText(String.valueOf(clicked.getNom()));
        txtprenom.setText(String.valueOf(clicked.getPrenom()));
        txtEmail.setText(String.valueOf(clicked.getEmail()));
        txtdescription.setText(String.valueOf(clicked.getDescription()));
        
    }
     
     
     
     void Videtable(){
          idcl.setCellValueFactory(data -> new SimpleObjectProperty(""));
        nomcl.setCellValueFactory(data -> new SimpleStringProperty(""));
        prenomcl.setCellValueFactory(data -> new SimpleStringProperty(""));
        emailcl.setCellValueFactory(data -> new SimpleStringProperty(""));
        descriptioncl.setCellValueFactory(data -> new SimpleStringProperty(""));
      
           tableReclamation.setItems(recList);
         
     }
     
     
      @FXML
    void Listresolutionbtn(ActionEvent event) {
        
         try {
                     Parent root = FXMLLoader.load(getClass().getResource("Tableresolution.fxml"));
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
    private void deleteResolution(ActionEvent event) {
        Reclamation clicked = tableReclamation.getSelectionModel().getSelectedItem();
        ServiceReclamation rc = new ServiceReclamation();
                   Alert alert4 = new Alert(Alert.AlertType.CONFIRMATION);
      alert4.setTitle("Confirmer ");
      alert4.setHeaderText("Confirmer");
      alert4.setContentText(" ");
             rc.supprimerReclamation(clicked.getId());
             textid.setText(null);
          txtnom.setText(null);
          txtprenom.setText(null);
          txtEmail.setText(null);
          txtdescription.setText(null);
          
          Videtable();
          afficherReclamation();
                     TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimé avec succés");
            tray.setMessage("Supprimé avec succés");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
         
    }


    @FXML
    private void updateResolution(ActionEvent event) {
        String id = textid.getText() ;
        String nom = txtnom.getText();
        String prenom = txtprenom.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String email = txtEmail.getText();
        String description = txtdescription.getText();
        
     
         if (nom.isEmpty() || prenom.isEmpty()|| email.isEmpty()|| description.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             Reclamation r=new Reclamation(Integer.valueOf(id),nom,prenom,email,description,comboboxuser.getValue());
             ServiceReclamation rc = new ServiceReclamation(); 
                Alert alert4 = new Alert(Alert.AlertType.CONFIRMATION);
      alert4.setTitle("Confirmer ");
      alert4.setHeaderText("Confirmer");
      alert4.setContentText(" ");
             
             rc.modifierReclamation(r);
             /*sendSMS sm = new sendSMS();
             sendSMS.sendSMS(r);*/
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Reclamation modifier  avec succes!");
                alert.showAndWait();
                
           
 
           textid.setText(null);
          txtnom.setText(null);
          txtprenom.setText(null);
          txtEmail.setText(null);
          txtdescription.setText(null);
          Videtable();
          afficherReclamation();
                  TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifié avec succés");
            tray.setMessage("Modifié avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
          
          
         }
    }

    @FXML
    private void afficherparno(ActionEvent event) throws SQLException {
        resetTableData();
    }
    
}
