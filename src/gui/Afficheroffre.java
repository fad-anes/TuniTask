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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import service.offreservice;
public class Afficheroffre implements Initializable{

    @FXML
    private Button retourm;
    @FXML
    private Button ref;
    @FXML
    private HBox hbox;
private Users u=new Users(42);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image i2 =new Image("images/icons8-available-updates-30.png");
        ImageView imageView = new ImageView(i2);
        ref.setGraphic(imageView);



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
                    c.setU(u);
                    hbox.getChildren().add(root);}
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    @FXML
    private void retourm(ActionEvent event) throws IOException {
       }
    @FXML
    private void ref(ActionEvent event) throws IOException {
        hbox.getChildren().clear();
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
                c.setU(u);
                hbox.getChildren().add(root);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
