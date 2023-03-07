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
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class TableresolutionController implements Initializable {

    @FXML
    private TableView<Reponserec> resolutionTable;
    @FXML
    private TableColumn<Reponserec,Integer > idcl;
    @FXML
    private TableColumn<Reponserec, String> nomAgcl;
    @FXML
    private TableColumn<Reponserec, String> prenomAgcl;
    @FXML
    private TableColumn<Reponserec, String> resolutioncl;
    @FXML
    private TextField textid;
    @FXML
    private TextField txtnomAg;
    @FXML
    private TextField txtprenomAg;
    @FXML
    private TextField txtresolution;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

      Connection mc;
    PreparedStatement ste;
     ObservableList<Reponserec>recList;
    
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
       
        
        String sql="select * from reponserec";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
            while(rs.next()){
                Reponserec r = new Reponserec();
                r.setId(rs.getInt("id"));
                r.setNomAgent(rs.getString("nomAgent"));
                r.setPrenomAgent(rs.getString("prenomAgent"));
                r.setResolution(rs.getString("resolution"));
                recList.add(r); 
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
         
      
        idcl.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getId()));
         
          nomAgcl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomAgent()));
       
       
        prenomAgcl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenomAgent()));
       
        resolutioncl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getResolution()));
       
        resolutionTable.setItems(recList);
        
    
        
    }
     
     
     
     
     void Videtable(){
         
         idcl.setCellValueFactory(data -> new SimpleObjectProperty(""));
         
          nomAgcl.setCellValueFactory(data -> new SimpleStringProperty(""));
       
       
        prenomAgcl.setCellValueFactory(data -> new SimpleStringProperty(""));
       
        resolutioncl.setCellValueFactory(data -> new SimpleStringProperty(""));
       
        resolutionTable.setItems(recList);
     }
     
     

    
    @FXML
    void getSelected(MouseEvent event) {
      Reponserec clicked = resolutionTable.getSelectionModel().getSelectedItem();
          textid.setText(String.valueOf(clicked.getId()));
         txtnomAg.setText(String.valueOf(clicked.getNomAgent()));
        txtprenomAg.setText(String.valueOf(clicked.getPrenomAgent()));
        txtresolution.setText(String.valueOf(clicked.getResolution()));
        
    }
    @FXML
    private void addResolution(ActionEvent event) {
        String id_rec = textid.getText();
        String nomAg = txtnomAg.getText();
        String prenomAg = txtprenomAg.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String resolution = txtresolution.getText();
        
     
         if (id_rec.isEmpty() || nomAg.isEmpty()|| prenomAg.isEmpty()|| resolution.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             
             Reponserec r=new Reponserec(Integer.valueOf(id_rec),nomAg,prenomAg,resolution);
             ServiceReponserec rc = new ServiceReponserec(); 
             rc.ajouterReponserec(r);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Resolution Ajoutée avec succes!");
                alert.showAndWait();
                
           
 
           textid.setText(null);
          txtnomAg.setText(null);
          txtprenomAg.setText(null);
          txtresolution.setText(null);
          Videtable();
         }
         afficherReclamation();
    }

    @FXML
    private void updateResolution(ActionEvent event) {
         String id_rec = textid.getText();
        String nomAg = txtnomAg.getText();
        String prenomAg = txtprenomAg.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String resolution = txtresolution.getText();
        
     
         if (id_rec.isEmpty() || nomAg.isEmpty()|| prenomAg.isEmpty()|| resolution.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             
             Reponserec r=new Reponserec(Integer.valueOf(id_rec),nomAg,prenomAg,resolution);
             ServiceReponserec rc = new ServiceReponserec(); 
             rc.modifierReponserec(r);
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Resolution modifier avec succes!");
                alert.showAndWait();
                
           
 
           textid.setText(null);
          txtnomAg.setText(null);
          txtprenomAg.setText(null);
          txtresolution.setText(null);
          Videtable();
         }
         afficherReclamation();
        
    }

    @FXML
    private void deleteResolution(ActionEvent event) {
        Reponserec clicked = resolutionTable.getSelectionModel().getSelectedItem();
        ServiceReponserec rc = new ServiceReponserec(); 
             rc.supprimerReponserec(clicked.getId());
             textid.setText(null);
          txtnomAg.setText(null);
          txtprenomAg.setText(null);
          txtresolution.setText(null);
          Videtable();
          afficherReclamation();
    }
    
}
