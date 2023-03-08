package gui;
import entite.offre;
import entite.commentaire;
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
import javafx.util.Duration;
import service.commentaireservice;
import org.controlsfx.control.Notifications;
public class Ajoutcomm implements Initializable{
    private int id;
    private Users u=new Users();

    public void setU(Users u) {
        this.u = u;
    }

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
            Notifications n=Notifications.create();
            n.title("ajouté avec succes");
            n.text("ajouté avec succes");
            n.hideAfter(Duration.seconds(5));
            n.position(Pos.CENTER_RIGHT);
            n.show();
        }
    }
    @FXML
    private void rt(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("voir commentaire.fxml"));
        Parent root=loader.load();

        VoirCommentaire dc=loader.getController();
        dc.setI(id);
        dc.setU(u);
        ajss.getScene().setRoot(root);
    }
}
