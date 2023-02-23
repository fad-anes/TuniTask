package GUI;

import entity.Questions;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceQuestions;


    public class DisplayQuestionsController {
        @FXML
        private TableView<Questions> tableViewQuestions;

        @FXML
        private TableColumn<Questions, Integer> columnQuestionId;

        @FXML
        private TableColumn<Questions, String> columnQuestionText;

        @FXML
        private TableColumn<Questions, Integer> columnQuizId;

        private ServiceQuestions serviceQuestions;

        public void initialize() {
            serviceQuestions = new ServiceQuestions();
            columnQuestionId.setCellValueFactory(new PropertyValueFactory<>("id_question"));
            columnQuestionText.setCellValueFactory(new PropertyValueFactory<>("question_text"));
            columnQuizId.setCellValueFactory((new PropertyValueFactory<>("quiz.id_quiz")));
            tableViewQuestions.getItems().addAll(serviceQuestions.readAll());
        }
    }

