package GUI;

import entity.Quizs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceQuizs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddQuiz implements Initializable {

    @FXML
    private Label idquiztitle;
    @FXML
    private Label iddescription;
    @FXML
    private Label idscore;

    @FXML
    private TextField titletextfield;
    @FXML
    private TextField desctextfield;
    @FXML
    private TextField scoretextfield;

    @FXML
    private Button idbtn;
    @FXML
    private Button idbtndisplayall;
    @FXML
    private Button idbtndisplayone;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void insert(ActionEvent event) {
        String title = titletextfield.getText().trim();
        String description = desctextfield.getText().trim();
        String scoreStr = scoretextfield.getText().trim();
        if (title.isEmpty() || description.isEmpty() || scoreStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }
        int score;
        try {
            score = Integer.parseInt(scoreStr);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid score");
            alert.setContentText("Please enter a valid integer for score.");
            alert.showAndWait();
            return;
        }
        if (title.isEmpty() || description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please enter a non-empty value for title and description.");
            alert.showAndWait();
            return;
        }

        Quizs quiz = new Quizs(title, description, score);
        ServiceQuizs sq = new ServiceQuizs();
        sq.insert(quiz);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz inserted");
        alert.setContentText("The quiz has been successfully inserted.");
        alert.showAndWait();
    }

        @FXML
    public void readAll(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("displayquizs.fxml"));
        Stage window=(Stage) idbtndisplayall.getScene().getWindow();
        window.setScene(new Scene(root));
    }







}
