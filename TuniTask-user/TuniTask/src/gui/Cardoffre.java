package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import entite.rate;
import entite.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.Rating;
import service.rateservice;

public class Cardoffre implements Initializable{
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
    private Rating rating;
    @FXML
    private Label ratlab;

    @FXML
    private Button btnvoir;

    private Users u=new Users();

    public void setU(Users u) {
        this.u = u;
    }

    private String[] colors={"#DDA0DD","#DA70D6","#BA55D3","#9370DB","#8A2BE2","#77119B","#DAC0FF","#EBCBF6"};
    int offreid;

    public void setdata(offre o)  {

        Image i =new Image("file:///"+o.getImg());
        String s2=String.valueOf(o.getSalaireH());
        image.setImage(i);
        titre.setText(o.getTitre());
        nom.setText(o.getNom());
        prenom.setText(o.getPrenom());
        descrip.setText(o.getDescription());
        offreid=o.getIdoffre();
        DecimalFormat df = new DecimalFormat("0.0");
        rateservice rs=new rateservice();

        rating.setRating(rs.calcul(o.getIdoffre()));
        ratlab.setText("Rating: "+df.format(rs.calcul(o.getIdoffre())));
       rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                float t;
                int t0 =t1.intValue();

                rateservice rs=new rateservice();
                DecimalFormat df = new DecimalFormat("0.0");
                rate r= new rate(o,u,t0);

                rs.insert(r);
                t=rs.calcul(o.getIdoffre());
                rating.setRating(t);
                ratlab.setText("Rating: "+df.format(t));

            }
        });


        sal.setText(s2+"Dt");
        box.setStyle("-fx-background-color: "+colors[(int)(Math.random()*colors.length)]+";"+
       "-fx-background-radius: 20;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10);");

    }


    @FXML
    private void versmessage(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("voir commentaire.fxml"));
        Parent root=loader.load();
        VoirCommentaire dc=loader.getController();
        dc.setI(offreid);
        dc.setU(u);
        btnvoir.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //System.out.println(r);
    }
}
