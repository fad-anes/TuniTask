package gui;

import entite.Answers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.ServiceAnswers;

public class Editanswer {

    @FXML
    private TextField answerTextField;

    @FXML
    private TextField isCorrectTextField;

    private Answers answer;

    public void setAnswer(Answers answer) {
        this.answer = answer;
        answerTextField.setText(answer.getAnswer());
        isCorrectTextField.setText(String.valueOf(answer.getIs_correct()));
    }

    @FXML
    private void save() {
        answer.setAnswer(answerTextField.getText());
        answer.setIs_correct(Boolean.valueOf(isCorrectTextField.getText()));
        ServiceAnswers serviceAnswers = new ServiceAnswers();
        serviceAnswers.update(answer, answer.getId_answer());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Answer updated");
        alert.setContentText("The answer has been successfully updated.");
        alert.showAndWait();
    }

    @FXML
    private void cancel() {
        answer = null;
        answerTextField.getScene().getWindow().hide();
    }

}
