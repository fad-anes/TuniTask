package gui;
import entite.offre;

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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.offreservice;
public class Afficheroffre implements Initializable{

    @FXML
    private Button retourm;

    @FXML
    private HBox hbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<offre> list=new ArrayList<>();
        offreservice ps=new offreservice();
        list=ps.readall();
                try {
                    for(int i=0;i<list.size();i++){
                        FXMLLoader fxl=new FXMLLoader();
                        fxl.setLocation(getClass().getResource("cardoffre.fxml"));
                        Parent root=fxl.load();
                    Cardoffre c=fxl.getController();
                    c.setdata(list.get(i));
                    hbox.getChildren().add(root);}
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    @FXML
    private void retourm(ActionEvent event) throws IOException {
       }

}
