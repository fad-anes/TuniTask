package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import entite.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.offreservice;
public class Menuoffre implements Initializable {
    @FXML
    private VBox box;


    @FXML
    private AnchorPane menu;
    @FXML
    private Button voffre;
    @FXML
    private Label titrelab;
    @FXML
    private Label descriptionla;
    @FXML
    private Label ajjss;
    @FXML
    private Label salairela;
    @FXML
    private TextField titre;
    @FXML
    private TextField salaire;
    @FXML
    private TextArea description;
    @FXML
    private Button aj;
    @FXML
    private Button Retour;
    @FXML
    private Button Actualiser;
    Users u=new Users(UserSession.getInstance().getId().intValue());



    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
        List<offre> list=new ArrayList<>();
        offreservice ps=new offreservice();
        list=ps.readall();
        try {
            for(int i=0;i<list.size();i++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("cardoffremessage.fxml"));
                Parent root=fxl.load();
                Cardoffremessage c=fxl.getController();
                c.setdata(list.get(i));
                box.getChildren().add(root);}
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Parent root;
        try {
            ( (Node) event.getSource()).getScene().getWindow().hide();
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
        }
    }
    @FXML
    private void voffre(ActionEvent event) throws IOException {
        box.getChildren().clear();
        List<offre> list=new ArrayList<>();
        offreservice ps=new offreservice();
        list=ps.ReadByIdd(u.getId());
        try {
            for(int i=0;i<list.size();i++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("voiroffre.fxml"));
                Parent root=fxl.load();
                Voiroffre c=fxl.getController();
                c.setdata(list.get(i));
                box.getChildren().add(root);}
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void Actualiser(ActionEvent event) throws IOException {
        box.getChildren().clear();
        List<offre> list=new ArrayList<>();
        offreservice ps=new offreservice();
        list=ps.readall();
        try {
            for(int i=0;i<list.size();i++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("cardoffremessage.fxml"));
                Parent root=fxl.load();
                Cardoffremessage c=fxl.getController();
                c.setdata(list.get(i));
                box.getChildren().add(root);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void aj(ActionEvent event) throws IOException {
        int t=0;

        offreservice ps=new offreservice();

        if(titre.getText().isEmpty()){
            t=1;
            this.titrelab.setText("champ manquant");}
        else this.titrelab.setText("");
        boolean isNumeric =  salaire.getText().matches("[+-]?\\d*(\\.\\d+)?");
        if(salaire.getText().isEmpty()){
            t=1;
            this.salairela.setText("champ manquant");}
        else if(!isNumeric){
            t=1;
            this.salairela.setText("salaire ne contient pas des caracteres");}
        else this.salairela.setText("");
        if(description.getText().isEmpty()){
            t=1;
            this.descriptionla.setText("champ manquant");}
        else this.descriptionla.setText("");

        if(t==0){
            offre p0=new offre(description.getText(),titre.getText(),Float.parseFloat(salaire.getText()),u);
            ps.insert(p0);
            Notifications n=Notifications.create();
            n.title("ajout?? avec succes");
            n.text("ajout?? avec succes");
            n.hideAfter(Duration.seconds(5));
            n.position(Pos.CENTER_RIGHT);
            n.show();
    }}

}
