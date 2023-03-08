package gui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMain extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

   //FXMLLoader fxmlLoader = new FXMLLoader(Displayquizs.class.getResource("displayquizs.fxml"));
     FXMLLoader fxmlLoader = new FXMLLoader(Displayquizs.class.getResource("displayquizsfreelancer.fxml"));

        Parent root = fxmlLoader.load();
        Image icon = new Image(getClass().getResourceAsStream("tunitaskimg.png"));
        Scene scene = new Scene(root);
        stage.setTitle("Quizs");
        stage.setScene(scene);
        stage.show();
stage.getIcons().add(icon);
    }
}


