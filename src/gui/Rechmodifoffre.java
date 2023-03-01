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
public class Rechmodifoffre implements Initializable {
    @FXML
    private TextField ido;
    @FXML
    private Button rech;
    @FXML
    private Label csidom;
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
    }
    @FXML
    private void recher(ActionEvent event) throws IOException {
      /*  offreservice ps=new offreservice();
        int t=0;
        if(ido.getText().isEmpty()){ t=1;
            this.csidom.setText("champ manquant");}
        else if(!ps.Findidof(Integer.parseInt(ido.getText()))){
            t=1;
            this.csidom.setText("Id introuvable");}
        else this.csidom.setText("");
        if(t==0){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("modifieroffre.fxml"));
            Parent root=loader.load();
            offre o=new offre();
            o=ps.ReadById(Integer.parseInt(ido.getText()));
            String s=String.valueOf(o.getFreelancer_id());
            String s2=String.valueOf(o.getSalaireH());
            Modifieroffre dc=loader.getController();
            dc.setId(s);
            dc.setTitre(o.getTitre());
            dc.setP(s2);
            dc.setD(o.getDescription());
            ido.getScene().setRoot(root);
        }*/
    }
    @FXML
    private void retourrecm(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("menuoffre.fxml"));
        Parent root=loader.load();

        Menuoffre dc=loader.getController();
        rech.getScene().setRoot(root);
    }
}
