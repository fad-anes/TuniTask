package GUI;

import entity.Answers;
import entity.Quizs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.Questions;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceAnswers;
import service.ServiceQuestions;
import service.ServiceQuizs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Displayquestionsfreelancer {

    @FXML
    private Label quizNameLabel;

    @FXML
    private TableView<Questions> questionsTable;

    @FXML
    private TableColumn<Questions, String> questionColumn;
    @FXML
    private Button cancelButton;
    private int quizId;

    private ServiceQuizs serviceQuizs = new ServiceQuizs();
    private ServiceQuestions serviceQuestions = new ServiceQuestions();

    public void setQuizId(int quizId) {
        this.quizId = quizId;
        loadQuestions();
    }

    private void loadQuestions() {
        // Get the quiz by ID
        Quizs quiz = serviceQuizs.readById(quizId);
        if (quiz == null) {
            // Quiz not found
            Alert alert = new Alert(Alert.AlertType.ERROR, "Quiz not found!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Set the quiz name label
        quizNameLabel.setText(quiz.getQuiz_title());

        // Load the questions into the table
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        List<Questions> questions = serviceQuestions.readByQuizId(quizId);
        questionsTable.getItems().setAll(questions);
    }

    public void editQuestion(ActionEvent event) {
        // Get the selected question
        Questions selectedQuestion = questionsTable.getSelectionModel().getSelectedItem();
        if (selectedQuestion == null) {
            // No question selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a question to edit!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Open the edit question dialog
        TextInputDialog dialog = new TextInputDialog(selectedQuestion.getQuestion());
        dialog.setTitle("Edit Question");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the new question text:");
        dialog.showAndWait().ifPresent(newQuestion -> {
            // Update the question
            selectedQuestion.setQuestion(newQuestion);
            serviceQuestions.update(selectedQuestion, selectedQuestion.getId());

            // Refresh the table
            List<Questions> questions = serviceQuestions.readByQuizId(quizId);
            questionsTable.getItems().setAll(questions);
        });
    }

    public void addQuestion(ActionEvent event) {
        // Open the add question dialog
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Question");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the question text:");
        dialog.showAndWait().ifPresent(newQuestion -> {
            // Create the new question
            Quizs quiz = serviceQuizs.readById(quizId);
            Questions newQuestionObj = new Questions(0, newQuestion, quiz);
            serviceQuestions.insert(newQuestionObj);

            // Refresh the table
            List<Questions> questions = serviceQuestions.readByQuizId(quizId);
            questionsTable.getItems().setAll(questions);
        });
    }

    public void deleteQuestion(ActionEvent event) {
        // Get the selected question
        Questions selectedQuestion = questionsTable.getSelectionModel().getSelectedItem();
        if (selectedQuestion == null) {
            // No question selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a question to delete!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Confirm the delete operation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected question?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                // Delete the question
                serviceQuestions.delete(selectedQuestion);


                // Refresh the table
                List<Questions> questions = serviceQuestions.readByQuizId(quizId);
                questionsTable.getItems().setAll(questions);
            }
        });
    }

    // Add this method to listen for double-click events on the table rows
    public void initialize() {
        questionsTable.setRowFactory(tv -> {
            TableRow<Questions> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    // Double-click detected
                    Questions question = row.getItem();

                    // Confirm the delete operation
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected question?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            // Delete the question
                            serviceQuestions.delete(question);

                            // Refresh the table
                            List<Questions> questions = serviceQuestions.readByQuizId(quizId);
                            questionsTable.getItems().setAll(questions);
                        }
                    });
                }
            });
            return row;
        });
    }




    @FXML
    private void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void viewQuestion() throws IOException {
        // Get the selected question
        Questions selectedQuestion = questionsTable.getSelectionModel().getSelectedItem();
        if (selectedQuestion == null) {
            // No question selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a question!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Load the answers for the selected question
        ServiceAnswers serviceAnswers = new ServiceAnswers();
        List<Answers> answers = serviceAnswers.getAnswersByQuestion(selectedQuestion);

        // Open the answers dialog
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewquestion.fxml"));
        Parent root = loader.load();
        Image icon = new Image(getClass().getResourceAsStream("tunitaskimg.png"));

        Viewquestion controller = loader.getController();
        controller.setQuestion(selectedQuestion);
        controller.setAnswers(answers);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void viewAnswers() throws IOException {
        // Get the selected question
        Questions selectedQuestion = questionsTable.getSelectionModel().getSelectedItem();
        UserSession session = UserSession.getInstance();
        session.setId_question(selectedQuestion);
        if (selectedQuestion == null) {
            // No question selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a question!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Load the answers for the selected question
        ServiceAnswers serviceAnswers = new ServiceAnswers();
        List<Answers> answers = serviceAnswers.getAnswersByQuestion(selectedQuestion);

        // Open the answers dialog
        FXMLLoader loader = new FXMLLoader(getClass().getResource("displayanswers.fxml"));
        Parent root = loader.load();
        Displayanswers controller = loader.getController();

        controller.setAnswers(answers);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }


}
