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
import javafx.stage.Stage;
import service.offreservice;
import service.commentaireservice;
public class Ajoutcomm implements Initializable{
    @FXML
    private Label cccm;
    @FXML
    private Label idcc;
    @FXML
    private Label ajss;
    @FXML
    private TextField tfid;
    @FXML
    private TextArea tacm;
    @FXML
    private Button aj;
    @FXML
    private Button rt;
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
    }
    @FXML
    private void aj(ActionEvent event) throws IOException {
        int t=0;
        commentaireservice ps=new commentaireservice();
        if(tfid.getText().isEmpty()){ t=1;
            this.idcc.setText("champ manquant");}
        else if(!ps.Findid(Integer.parseInt(tfid.getText()))){
            t=1;
            this.idcc.setText("offre introuvable");}
        else this.idcc.setText("");
        if(tacm.getText().isEmpty()){
            t=1;
            this.cccm.setText("champ manquant");}
        else this.cccm.setText("");
        if(t==0){
            commentaire p0=new commentaire(Integer.parseInt(tfid.getText()),tacm.getText());
            ps.insert(p0);
            this.ajss.setText("ajouté avec succes!");
        }
    }
    @FXML
    private void rt(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("voir commentaire.fxml"));
        Parent root=loader.load();

        VoirCommentaire dc=loader.getController();
        ajss.getScene().setRoot(root);
    }
}
