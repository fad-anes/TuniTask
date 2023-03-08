package gui;

import entite.Answers;
import entite.Questions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceAnswers;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Displayanswers implements Initializable {

    @FXML
    private TableView<Answers> answersTable;
    @FXML
    private TableColumn<Answers, Integer> idanswerColumn;
    @FXML
    private TableColumn<Answers, Integer> idQuestionColumn;
    @FXML
    private TableColumn<Answers, Integer> idQuizColumn;
    @FXML
    private TableColumn<Answers, String> answerColumn;
    @FXML
    private TableColumn<Answers, Boolean> iscorrectColumn;

    private List<Answers> answers = new ArrayList<>();
    private ServiceAnswers serviceAnswers = new ServiceAnswers();

    private Questions selectedQuestion;

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;

    }

    public void setSelectedQuestion(Questions selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idanswerColumn.setCellValueFactory(new PropertyValueFactory<>("id_answer"));
        idQuestionColumn.setCellValueFactory(new PropertyValueFactory<>("id_question"));
        idQuizColumn.setCellValueFactory(new PropertyValueFactory<>("id_quiz"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        iscorrectColumn.setCellValueFactory(new PropertyValueFactory<>("is_correct"));
        if (selectedQuestion != null){
            answers = serviceAnswers.getAnswersByQuestion(selectedQuestion);
            if (answers != null) {
                System.out.println("Answers list size: " + answers.size());
                answersTable.setItems(FXCollections.observableArrayList(answers));
            } else {
                System.out.println("Answers list is null or empty");
            }
        }
        System.out.println("no question selected");
    }
}