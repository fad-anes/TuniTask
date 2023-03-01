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
import javafx.stage.Stage;
import service.offreservice;
public class Ajouteroffre implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField titre;
    @FXML
    private TextField p;
    @FXML
    private TextArea d;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Retour;
    @FXML
    private Label csid;
    @FXML
    private Label ajss;
    @FXML
    private Label cstitre;
    @FXML
    private Label csp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void ajoffre(ActionEvent event) throws IOException {
     /*   int t=0;

        offreservice ps=new offreservice();
        if(id.getText().isEmpty()){ t=1;
            this.csid.setText("champ manquant");}
        else if(!ps.Findid(Integer.parseInt(id.getText()))){
            t=1;
            this.csid.setText("Id introuvable");}
        else this.csid.setText("");
        if(titre.getText().isEmpty()){
            t=1;
            this.cstitre.setText("champ manquant");}
        else this.cstitre.setText("");
        if(p.getText().isEmpty()){
            t=1;
            this.csp.setText("champ manquant");}
        else this.csp.setText("");

        if(t==0){
            offre p0=new offre(Integer.parseInt(id.getText()),d.getText(),
                    titre.getText(),Float.parseFloat(p.getText()));
            ps.insert(p0);
            this.ajss.setText("ajouté avec succes!");
        }*/


    }
    @FXML
    private void retouraj(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("menuoffre.fxml"));
        Parent root=loader.load();

        Menuoffre dc=loader.getController();
        titre.getScene().setRoot(root);
    }
}
