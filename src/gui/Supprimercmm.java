package gui;
import entite.offre;
import entite.commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.offreservice;
import service.commentaireservice;
public class Supprimercmm implements Initializable{
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label commentaire;
    @FXML
    private Button supp;
      int id;
    private String[] colors={"#DDA0DD","#DA70D6","#BA55D3","#9370DB","#8A2BE2","#77119B","#DAC0FF","#EBCBF6"};


    @Override
    public void initialize(URL url, ResourceBundle rb)  {

    }
    public void setdata(commentaire c)  {
        Image i =new Image(c.getImg());
        img.setImage(i);
        nom.setText(c.getName());
        prenom.setText(c.getPrename());
        commentaire.setText(c.getCommentaire());
       id =c.getIdcommentaire();
        System.out.println(c.getIdcommentaire());
        box.setStyle("-fx-background-color: "+colors[(int)(Math.random()*colors.length)]+";"+
                "-fx-background-radius: 20;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10);");

    }
    @FXML
    private void supp(ActionEvent event) throws IOException {
        commentaireservice ps=new commentaireservice();
        ps.delete(this.id);
    }
}
