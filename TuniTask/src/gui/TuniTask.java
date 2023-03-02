/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
//import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author abdes
 */
public class TuniTask extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
         Parent   root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene scene = new Scene(root);
                
                primaryStage.setTitle("TuniTask");
                primaryStage.centerOnScreen();
                primaryStage.setResizable(false);
                primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
                primaryStage.setScene(scene);
                
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(TuniTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
