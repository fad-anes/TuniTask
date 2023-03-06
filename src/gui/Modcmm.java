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
import javafx.scene.Parent;
import javafx.scene.control.*;
import service.commentaireservice;
public class Modcmm implements Initializable{
    @FXML
    private Label cccm;
    @FXML
    private Label ajss;
    @FXML
    private Button rt;
    @FXML
    private TextArea tacm;
    @FXML
    private Button mod;
private int id;

    public void setId(int id) {
        this.id = id;
    }

    private Users u= new Users();

    public void setU(Users u) {
        this.u = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO;
          }
    @FXML
    private void rt(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("voir commentaire.fxml"));
        Parent root=loader.load();

        VoirCommentaire dc=loader.getController();
        dc.setI(id);
        dc.setU(u);
        mod.getScene().setRoot(root);
    }
    @FXML
    private void mod(ActionEvent event) throws IOException {
        int t=0;
        int t0=0;
        offre o=new offre(id);
        commentaireservice ps=new commentaireservice();

        t0=ps.Findcomenus(u.getId());
        if(t0==0){
            t=1;
            this.ajss.setText("vous navez pas du commentaire a modifier");}
        else this.ajss.setText("");
        if(tacm.getText().isEmpty()){
            t=1;
            this.cccm.setText("champ manquant");}
        else this.cccm.setText("");
        if(t==0){
            commentaire c0=new commentaire(o,tacm.getText(),u);
            ps.update(t0,c0,o,u);
            this.ajss.setText("commentaire modifier");

        }
    }

}
