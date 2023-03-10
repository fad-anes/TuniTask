package gui;

//import com.sun.tools.javac.Main;
import entite.Answers;
import entite.Questions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.ServiceQuizs;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.List;

public class Viewquestion {

    @FXML
    private Label questionTextLabel;

    @FXML
    private ListView<String> answersListView;

    @FXML
    private Button checkAnswerButton;

    @FXML
    private Button nextButton;

    private List<Answers> answers;

    private ObservableList<String> answerStrings = FXCollections.observableArrayList();

    private int currentAnswerIndex = -1;
    private int score = 0;
    @FXML
    void backToQuestons() {
        Stage stage = (Stage) questionTextLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void checkAnswer() {
        String selectedAnswer = answersListView.getSelectionModel().getSelectedItem();
        if (selectedAnswer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select an answer.");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getAnswer().equals(selectedAnswer)) {
                currentAnswerIndex = i;
                break;
            }
        }
        boolean isCorrect = answers.get(currentAnswerIndex).getIs_correct();
        answersListView.getItems().forEach(answer -> {
            if (answer.equals(selectedAnswer)) {
                if (isCorrect) {
                    answersListView.setStyle("-fx-control-inner-background: green;");
                    score++;
                    userSess us = userSess.getInstance();
                    int quizscore=us.getId_quiz().getScore();
                    us.getId_quiz().setScore(quizscore+score);
                    ServiceQuizs serviceQuizs = new ServiceQuizs();
                    serviceQuizs.updateQuizScore(us.getId_quiz().getId_quiz(), quizscore + score);

                } else {
                    answersListView.setStyle("-fx-control-inner-background: red;");
                }
            }
        });
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Answer Result");
        alert.setHeaderText(null);
        alert.setContentText(isCorrect ? "Correct!" : "Incorrect.");
        alert.showAndWait();
        checkAnswerButton.setDisable(true);
        nextButton.setDisable(false);
    }
    @FXML
    void showScore(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Score");
        alert.setHeaderText(null);
        alert.setContentText("Your score is " + score + " out of " + 1 + "in the quiz ");

        Notifications notificationBuilder = Notifications.create()
                .title("Quiz Score")
                .text("Your score is " + score + " out of " + 1)
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked on Notification");
                    }
                });

        notificationBuilder.darkStyle();
        notificationBuilder.show();

        alert.showAndWait();

        // Close the window
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }







    public void setQuestion(Questions question) {

        questionTextLabel.setText(question.getQuestion());
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
        answerStrings.clear();
        for (Answers answer : answers) {
            answerStrings.add(answer.getAnswer());
        }
        answersListView.setItems(answerStrings);
        answersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        currentAnswerIndex = -1;
        checkAnswerButton.setDisable(false);
        nextButton.setDisable(true);
    }



}
