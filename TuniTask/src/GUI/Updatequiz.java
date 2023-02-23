package GUI;

import java.io.IOException;

import entity.Quizs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceQuizs;


public class Updatequiz {
    @FXML
    private TextField quizIdField;
    @FXML
    private TextField quizTitleField;
    @FXML
    private TextArea quizDescriptionArea;
    @FXML
    private TextField scoreField;

    private String quizId;
    private ServiceQuizs serviceQuizs;

    public void setQuizId(String quizId) {
        this.quizId = quizId;
        Quizs quiz = serviceQuizs.readById(Integer.parseInt(quizId));
        quizIdField.setText(String.valueOf(quiz.getId_quiz()));
        quizTitleField.setText(quiz.getQuiz_title());
        quizDescriptionArea.setText(quiz.getQuiz_description());
        scoreField.setText(String.valueOf(quiz.getScore()));
    }

    public void initialize() {
        serviceQuizs = new ServiceQuizs();
    }

    public void updateQuiz() {
        try {
            int id = Integer.parseInt(quizIdField.getText());
            String title = quizTitleField.getText();
            String description = quizDescriptionArea.getText();
            int score = Integer.parseInt(scoreField.getText());

            Quizs quiz = new Quizs(id, title, description, score);
            serviceQuizs.update(quiz, id);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Quiz updated successfully!", ButtonType.OK);
            alert.showAndWait();

            Stage stage = (Stage) quizIdField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid quiz ID and score!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void cancelUpdate() {
        Stage stage = (Stage) quizIdField.getScene().getWindow();
        stage.close();
    }

//    public void openUpdateQuiz(String quizId) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("updatequiz.fxml"));
//            Parent root = loader.load();
//
//            Updatequiz controller = loader.getController();
//            controller.setQuizId(quizId);
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
