package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entite.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import service.offreservice;
public class Internautevoir implements Initializable{
    @FXML
    private VBox box;
    @FXML
    private AnchorPane menu;
    @FXML
    private Button Retour;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<offre> list=new ArrayList<>();
        offreservice ps=new offreservice();
        list=ps.readall();
        try {
            for(int i=0;i<list.size();i++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("cardoffremessage.fxml"));
                Parent root=fxl.load();
                Cardoffremessage c=fxl.getController();
                c.setdata(list.get(i));
                box.getChildren().add(root);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Retour(ActionEvent event) throws IOException {}
}
