package gui;
import entite.offre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.offreservice;
public class Menuoffre implements Initializable {
    @FXML
    private HBox box;


    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
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

}
