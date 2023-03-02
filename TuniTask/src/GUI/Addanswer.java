package GUI;

import entity.Answers;
import entity.Questions;
import entity.Quizs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceAnswers;
import service.ServiceQuestions;
import service.ServiceQuizs;

import java.io.IOException;

public class Addanswer {

    @FXML
    private Button idcancel;
    @FXML
    private TextField answertextfield;
    @FXML
    private TextField iscorrecttextfield;

    public void insertanswer(ActionEvent event) {
        UserSession us = UserSession.getInstance();
        Questions idquestion = us.getId_question();
        Quizs idquiz = us.getId_quiz();
        ServiceAnswers sa = new ServiceAnswers();
        Answers a = new Answers();
        a.setQuestion_id(idquestion);
        a.setQuiz_id(idquiz);
        a.setAnswer(answertextfield.getText());
        a.setIs_correct(Boolean.valueOf(iscorrecttextfield.getText()));
        sa.insert(a);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Answer inserted");
        alert.setContentText("The answer has been successfully inserted.");
        alert.showAndWait();
    }

    public void readAll(ActionEvent event) throws IOException {
        Stage stage = (Stage) idcancel.getScene().getWindow();
        stage.close();
    }
}
