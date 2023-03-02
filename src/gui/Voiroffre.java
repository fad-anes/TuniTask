package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import service.offreservice;

import javax.imageio.ImageIO;
public class Voiroffre {
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private ImageView del;
    @FXML
    private ImageView mod;
    @FXML
    private Button modd;
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
    String s0=new String();
    String s1=new String();
    String s3=new String();
    public void setdata(offre o)  {
        offreservice y=new offreservice();
        Image i =new Image(o.getImg());
        Image i2 =new Image("images/téléchargement-removebg-preview.png");
        String s2=String.valueOf(o.getSalaireH());
        image.setImage(i);
        titre.setText(o.getTitre());
        nom.setText(o.getNom());
        prenom.setText(o.getPrenom());
        descrip.setText(o.getDescription());
        offreid=o.getIdoffre();
        sal.setText(s2+"Dt");
        del.setImage(i2);

        s0=s2;
        s1=o.getTitre();
        s3=o.getDescription();
        del.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                y.delete(o.getIdoffre());
            }
        });
        /*mod.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {


                try {

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });*/
        rate.setRating(o.getRate());
        ratelab.setText("Rating: "+o.getRate());
        box.setStyle("-fx-background-color: "+colors[(int)(Math.random()*colors.length)]+";"+
                "-fx-background-radius: 20;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10);");

    }
    @FXML
    private void modd(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("modifieroffre.fxml"));
        Parent root=loader.load();
        Modifieroffre dc=loader.getController();
        dc.setD(s3);
        dc.setP(s1);
        dc.setTitre(s0);
        dc.setIdd(offreid);
        modd.getScene().setRoot(root);
    }
}