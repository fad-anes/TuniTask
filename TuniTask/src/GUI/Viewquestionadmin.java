package GUI;

import entity.Answers;
import entity.Questions;
import entity.Quizs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.ServiceAnswers;
import service.ServiceQuestions;
import service.ServiceQuizs;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class Viewquestionadmin {

    @FXML
    private Label questionTextLabel;



    @FXML
    private TableView<Answers> answersTableView;

    @FXML
    private TableColumn<Answers, String> answerColumn;

    @FXML
    private TableColumn<Answers, String> isCorrectColumn;

    private List<Answers> answers;

    private ObservableList<String> answerStrings = FXCollections.observableArrayList();
    private ObservableList<Boolean> isCorrectStrings = FXCollections.observableArrayList();
@FXML
private Button addbtn;
@FXML
private Button editbtn;
@FXML
private Button deletebtn;
    @FXML
    void backToQuestons() {
        Stage stage = (Stage) questionTextLabel.getScene().getWindow();
        stage.close();
    }

    public void setQuestion(Questions question) {
        questionTextLabel.setText(question.getQuestion());

    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;


        // populate the table view
        ObservableList<Answers> data = FXCollections.observableArrayList(answers);
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        isCorrectColumn.setCellValueFactory(new PropertyValueFactory<>("is_correct"));
        answersTableView.setItems(data);
    }

    public void delete(ActionEvent event) {
        // get the selected answer
        Answers selectedAnswer = answersTableView.getSelectionModel().getSelectedItem();
        if (selectedAnswer == null) {
            // no answer selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an answer to delete.");
            alert.showAndWait();
        } else {
            // confirm if the user really wants to delete the selected answer
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected answer?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                // delete the selected answer
                ServiceAnswers serviceAnswers = new ServiceAnswers();
                serviceAnswers.delete(selectedAnswer);

                // refresh the view
                ObservableList<Answers> data = FXCollections.observableArrayList(serviceAnswers.getAnswersByQuestion(selectedAnswer.getQuestion_id()));
                answersTableView.setItems(data);
            }
        }
    }


    public void edit(ActionEvent event) throws IOException {
        // get the selected answer
        Answers selectedAnswer = answersTableView.getSelectionModel().getSelectedItem();
        if (selectedAnswer == null) {
            // no answer selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an answer to edit.");
            alert.showAndWait();
        } else {
            // load the edit answer view and pass in the selected answer
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editanswer.fxml"));
            Parent root = loader.load();
            Editanswer controller = loader.getController();
            controller.setAnswer(selectedAnswer);
            Stage window = (Stage) editbtn.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    public void add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addanswer.fxml"));
        Stage window = (Stage) addbtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
