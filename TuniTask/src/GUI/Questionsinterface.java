package GUI;

import entity.Questions;
import entity.Quizs;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceQuestions;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Questionsinterface implements Initializable {

    private Quizs quiz;
    private List<Questions> questions;
    private int selectedQuestionId;

    public void setQuizAndQuestions(Quizs quiz, int selectedQuestionId) {
        this.quiz = quiz;
        ServiceQuestions sq=new ServiceQuestions();
        List<Questions> questions = sq.readAll();// fetch questions from the database based on quiz
        if (questions != null && !questions.isEmpty()) {
            this.questions = questions;
        } else {
            // Display an appropriate message to the user
            System.out.println("No questions found");
        }
        this.selectedQuestionId = selectedQuestionId;
    }

    @FXML
    private TableView<Questions> questionTable;

    @FXML
    private TableColumn<Questions, Integer> questionIdColumn;

    @FXML
    private TableColumn<Questions, String> questionTextColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_question"));
        questionTextColumn.setCellValueFactory(new PropertyValueFactory<>("question_text"));
        if (questions != null && !questions.isEmpty()) {
            questionTable.setItems(FXCollections.observableArrayList(questions));
            questionTable.getSelectionModel().select(selectedQuestionId);
        } else {
            // Display an appropriate message to the user
            questionTable.setPlaceholder(new Label("No questions found"));
        }
    }
}
