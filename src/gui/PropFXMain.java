/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mohamed gabsi
 */
public class PropFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        Parent root=FXMLLoader.load(getClass().getResource("InterfaceProposition.fxml"));
            
           Scene scene = new Scene(root);
        //Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Menu Proposition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
