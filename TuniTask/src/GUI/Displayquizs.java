package GUI;

import entity.Questions;
import entity.Quizs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceQuestions;
import service.ServiceQuizs;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class Displayquizs {
   @FXML
    private TableView<Quizs> quiztable;

    @FXML
    private TableColumn<Quizs, Integer> idquiz;

    @FXML
    private TableColumn<Quizs, String> idtitle;

    @FXML
    private TableColumn<Quizs, String> iddesc;

    @FXML
    private TableColumn<Quizs, Integer> idscore;
    @FXML
    private Button insertbtn;

    private ServiceQuizs service = new ServiceQuizs();
    private ObservableList<Quizs> quizList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set the cell value factory for each column
        idquiz.setCellValueFactory(new PropertyValueFactory<>("id_quiz"));
        idtitle.setCellValueFactory(new PropertyValueFactory<>("quiz_title"));
        iddesc.setCellValueFactory(new PropertyValueFactory<>("quiz_description"));
        idscore.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Get all the quizzes from the database and add them to the observable list
        quizList.addAll(service.readAll());

        // Set the observable list to the table view
        quiztable.setItems(quizList);
    }

    public void loadAddQuiz(ActionEvent event) throws IOException {
        Parent addQuizParent = FXMLLoader.load(getClass().getResource("quizInterface.fxml"));
        Scene addQuizScene = new Scene(addQuizParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addQuizScene);
        window.show();
    }

    public void displayQuizs(ActionEvent event) {
        // Load the quiz table view
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("displayquizs.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Quizs");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void insert(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("quizInterface.fxml"));
        Stage window = (Stage) insertbtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void delete(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Quiz");
        dialog.setHeaderText("Enter the ID of the quiz you want to delete:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quizId -> {
            try {
                int id = Integer.parseInt(quizId);

                Quizs quiz = service.readById(id);
                if (quiz == null) {
                    // Quiz not found
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Quiz not found!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    service.delete(quiz);
                    // Remove the quiz from the list of quizzes displayed in the table
                    quizList.removeIf(q -> q.getId_quiz() == id);

                    // refresh the table view
                    quiztable.refresh();
                }
            } catch (NumberFormatException e) {
                // Invalid ID
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid quiz ID!", ButtonType.OK);
                alert.showAndWait();
            }
        });
    }



    @FXML
    public void update(ActionEvent event) throws IOException {
        // show an input dialog to get the quiz ID from the user
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update Quiz");
        dialog.setHeaderText("Enter the ID of the quiz to update:");
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quizId -> {
            try {
                int id = Integer.parseInt(quizId);
                Quizs quiz = service.readById(id);
                if (quiz == null) {
                    // Quiz not found
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Quiz not found!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // open the Updatequiz view with the selected quiz data
                    openUpdateQuiz(quizId);
                }
            } catch (NumberFormatException e) {
                // Invalid ID
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid quiz ID!", ButtonType.OK);
                alert.showAndWait();
            }
        });
    }
    public void openUpdateQuiz(String quizId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updatequiz.fxml"));
            Parent root = loader.load();

            Updatequiz controller = loader.getController();
            controller.setQuizId(quizId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void displayquestions(ActionEvent event) {
        // show an input dialog to get the quiz ID from the user
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Display Questions");
        dialog.setHeaderText("Enter the ID of the quiz to display its questions:");
        dialog.setContentText("Quiz ID:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quizId -> {
            try {
                int id = Integer.parseInt(quizId);
                Quizs quiz = service.readById(id);
                if (quiz == null) {
                    // Quiz not found
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Quiz not found!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Load the FXML file for displaying questions
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("displayquestions.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the FXML file
                    Displayquestions controller = loader.getController();

                    // Pass the quiz ID to the controller
                    controller.setQuizId(Integer.parseInt(quizId));

                    // Create the new scene
                    Scene scene = new Scene(root);

                    // Create a new stage and set the scene
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Quiz " + id + " Questions");

                    // Show the new stage
                    stage.show();
                }
            } catch (NumberFormatException e) {
                // Invalid ID
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid quiz ID!", ButtonType.OK);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
