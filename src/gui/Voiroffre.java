package gui;
import entite.offre;
import java.io.IOException;
import java.text.DecimalFormat;

import entite.rate;
import entite.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import service.offreservice;
import service.rateservice;

public class Voiroffre {
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Button del;
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
    Users uu=new Users();
    String s0=new String();
    String s1=new String();
    String s3=new String();
    public void setdata(offre o)  {
        float t;
        Users u=new Users(o.getUser_id().getId());
        uu=u;
        offreservice y=new offreservice();
        Image i =new Image("file:///"+o.getImg());
        Image i2 =new Image("images/icons8-remove-24.png");
        ImageView imageView = new ImageView(i2);
        String s2=String.valueOf(o.getSalaireH());
        image.setImage(i);
        titre.setText(o.getTitre());
        nom.setText(o.getNom());
        prenom.setText(o.getPrenom());
        descrip.setText(o.getDescription());
        offreid=o.getIdoffre();
        sal.setText(s2+"Dt");
        del.setGraphic(imageView);

        s0=s2;
        s1=o.getTitre();
        s3=o.getDescription();
       entite.rate r= new rate();
        rateservice rs=new rateservice();
        t=rs.calcul(o.getIdoffre());
        DecimalFormat df = new DecimalFormat("0.0");
        /*rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                //ratlab.setText("Rating: "+t1);
            }
        });*/
        rate.setRating(t);
        ratelab.setText("Rating: "+df.format(t));
        rate.setDisable(true);


        box.setStyle("-fx-background-color: "+colors[(int)(Math.random()*colors.length)]+";"+
                "-fx-background-radius: 20;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10);");

    }
    @FXML
    private void del(ActionEvent event) throws IOException {
        offreservice y=new offreservice();
        y.delete(offreid);
        Notifications n=Notifications.create();
        n.title("supp avec succes");
        n.text("supp avec succes");
        n.hideAfter(Duration.seconds(5));
        n.position(Pos.CENTER_RIGHT);
        n.show();
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
        dc.setU(uu);
        modd.getScene().setRoot(root);
    }
}