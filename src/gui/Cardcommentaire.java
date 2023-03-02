package gui;
import entite.offre;
import entite.commentaire;
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
import service.commentaireservice;

import javax.imageio.ImageIO;
public class Cardcommentaire {
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    private String[] colors={"#DDA0DD","#DA70D6","#BA55D3","#9370DB","#8A2BE2","#77119B","#DAC0FF","#EBCBF6"};
    @FXML
    private Label commentaire;
    public void setdata(commentaire c)  {
        Image i =new Image(c.getImg());
        img.setImage(i);
        nom.setText(c.getName());
        prenom.setText(c.getPrename());
        commentaire.setText(c.getCommentaire());

        box.setStyle("-fx-background-color: "+colors[(int)(Math.random()*colors.length)]+";"+
                "-fx-background-radius: 20;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10);");

    }
}
