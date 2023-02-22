package gui;
import entite.offre;
import entite.commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.offreservice;
import service.commentaireservice;
public class Modcmm implements Initializable{
    @FXML
    private Label cmcc;
    @FXML
    private Label idccc;
    @FXML
    private Label mdss;
    @FXML
    private TextArea cmta;
    @FXML
    private TextField idtf;
    @FXML
    private Button ch;
    @FXML
    private Button md;
    @FXML
    private Button retour;
    @FXML
    private Label ido;
    @FXML
    private Label idocc;
    @FXML
    private TextField idotf;

    public void setCmta(String cmta) {
        this.cmta.setText(cmta);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO;
          }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("voir commentaire.fxml"));
        Parent root=loader.load();

        VoirCommentaire dc=loader.getController();
        retour.getScene().setRoot(root);
    }
    @FXML
    private void ch(ActionEvent event) throws IOException {
        commentaireservice ps=new commentaireservice();
        commentaire c0=new commentaire();
        int t=0;
        if(idtf.getText().isEmpty()){ t=1;
            this.idccc.setText("champ manquant");}
        else if(!ps.Findidcm(Integer.parseInt(idtf.getText()))){
            t=1;
            this.idccc.setText("commentaire introuvable");}
        else this.idccc.setText("");
        if(t==0){
            c0=ps.ReadById(Integer.parseInt(idtf.getText()));
            String s=String.valueOf(c0.getOffre_id());
            this.idotf.setText(s);
            this.cmta.setText(c0.getCommentaire());

        }
    }
    @FXML
    private void md(ActionEvent event) throws IOException {
        int t=0;
        commentaireservice ps=new commentaireservice();
        if(cmta.getText().isEmpty()){ t=1;
            this.cmcc.setText("champ manquant");}
        else this.cmcc.setText("");
        if(idotf.getText().isEmpty()){ t=1;
            this.idocc.setText("champ manquant");}
        else this.idocc.setText("");
        if(t==0){
            commentaire c0=new commentaire(Integer.parseInt(idotf.getText()),cmta.getText());
            ps.update((Integer.parseInt(idtf.getText())),c0);
            this.mdss.setText("modifié avec succès");
        }
    }
}
