package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entite.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.offreservice;
public class Modifieroffre implements Initializable{
    @FXML
    private TextField id;
    @FXML
    private TextField titre;
    @FXML
    private TextField p;
    @FXML
    private TextArea d;
    @FXML
    private Button aj;
    @FXML
    private Button retour;
    @FXML
    private Label csid;
    @FXML
    private Label cstitre;
    @FXML
    private Label csp;
    @FXML
    private Label mss;
    Users u=new Users();

    public void setU(Users u) {
        this.u = u;
    }

    private int idd;

    public void setIdd(int idd) {
        this.idd = idd;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
    }
    @FXML
    private void mdoffre(ActionEvent event) throws IOException {
        int t=0;

        offreservice ps=new offreservice();
        boolean isNumeric =  titre.getText().matches("[+-]?\\d*(\\.\\d+)?");
        if(titre.getText().isEmpty()){
            t=1;
            this.cstitre.setText("champ manquant");}
        else if(!isNumeric){
            t=1;
            this.cstitre.setText("salaire ne contient pas des caracteres");}
        else this.cstitre.setText("");
        if(p.getText().isEmpty()){
            t=1;
            this.csp.setText("champ manquant");}
        else this.csp.setText("");

        if(t==0){
            offre p0=new offre(d.getText(),
                    p.getText(),Float.parseFloat(titre.getText()),u);
            ps.update(idd,p0);

            Notifications n=Notifications.create();
            n.title("modifié avec succès");
            n.text("modifié avec succès");
            n.hideAfter(Duration.seconds(5));
            n.position(Pos.CENTER_RIGHT);
            n.show();
        }

    }
    @FXML
    private void retouraj(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("menuoffre.fxml"));
        Parent root=loader.load();

        Menuoffre dc=loader.getController();
        cstitre.getScene().setRoot(root);
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    public void setP(String p) {
        this.p.setText(p);
    }

    public void setD(String d) {
        this.d.setText(d);
    }
}
