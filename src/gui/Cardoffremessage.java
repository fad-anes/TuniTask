package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import service.offreservice;

import javax.imageio.ImageIO;
public class Cardoffremessage {
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label descrip;
    @FXML
    private Label sal;
    @FXML
    private Button btnvoir;
    @FXML
    private Rating rate;
    @FXML
    private Label ratelab;
    private String[] colors={"#DDA0DD","#DA70D6","#BA55D3","#9370DB","#8A2BE2","#77119B","#DAC0FF","#EBCBF6"};
    int offreid;
    public void setdata(offre o)  {

        Image i =new Image(o.getImg());
        String s2=String.valueOf(o.getSalaireH());
        image.setImage(i);
        titre.setText(o.getTitre());
        nom.setText(o.getNom());
        prenom.setText(o.getPrenom());
        descrip.setText(o.getDescription());
        offreid=o.getIdoffre();
        sal.setText(s2+"Dt");

        rate.setRating(o.getRate());
        ratelab.setText("Rating: "+o.getRate());
        box.setStyle("-fx-background-color: "+colors[(int)(Math.random()*colors.length)]+";"+
                "-fx-background-radius: 20;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10);");

    }
}
