package gui;
import entite.offre;
import entite.commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entite.user;
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
import service.commentaireservice;
public class Ajoutcomm implements Initializable{
    private int id;
    user u=new user(30,"yji@gmail.com","lamia","bh","C:/Users/anes_//OneDrive/Bureau/tt/src/images/5231019.png");
    @FXML
    private Button aj;
    @FXML
    private Button rt;
    @FXML
    private Label cccm;
    @FXML
    private Label ajss;
    @FXML
    private TextArea tacm;
    public void setId(int id) {
        this.id = id;
    }



    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
    }
    @FXML
    private void aj(ActionEvent event) throws IOException {

       int t=0;
        commentaireservice ps=new commentaireservice();
        offre o=new offre(id);
        if(tacm.getText().isEmpty()){
            t=1;
            this.cccm.setText("champ manquant");}
        else this.cccm.setText("");
        if(t==0){
            commentaire p0=new commentaire(o,tacm.getText(),u);
            ps.insert(p0,o,u);
            this.ajss.setText("ajouté avec succes!");
        }
    }
    @FXML
    private void rt(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("voir commentaire.fxml"));
        Parent root=loader.load();

        VoirCommentaire dc=loader.getController();
        dc.setI(id);
        ajss.getScene().setRoot(root);
    }
}
