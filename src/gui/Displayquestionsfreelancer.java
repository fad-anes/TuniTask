package gui;

import entite.Answers;
import entite.Quizs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import entite.Questions;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import service.ServiceAnswers;
import service.ServiceQuestions;
import service.ServiceQuizs;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

public class Displayquestionsfreelancer {

    public Button certificateButton;
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
    private userSess userSess;
    public void setQuizId(int quizId) {
        this.quizId = quizId;
        // Retrieve the UserSession instance that was used to store the score
        userSess = userSess.getInstance();

        loadQuestions();
    }
    private static File generatePdf(String content) throws IOException {
        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        String imagePath = Displayquestionsfreelancer.class.getResource("tunitaskimg.png").getPath();
        File imageFile = new File(imagePath);
        PDImageXObject badgeImage = PDImageXObject.createFromFileByContent(imageFile, document);

        // Load the signature image
        String signaturePath = Displayquestionsfreelancer.class.getResource("signature.jpg").getPath();
        File signatureFile = new File(signaturePath);
        PDImageXObject signatureImage = PDImageXObject.createFromFileByContent(signatureFile, document);


        // Create a new content stream for the page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
// Set the certificate title
        String title = "Certificate of Completion";
        float titleWidth = new PDType1Font(HELVETICA_BOLD).getStringWidth(title) / 1000 * 32;
        contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 18);

        contentStream.beginText();
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, 700);
        contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 18);
        contentStream.showText(title);
        contentStream.endText();

        // Set the name of the quiz taker
//        String name = UserSession.getInstance().getUser().getFullname();
//        float nameWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(name) / 1000 * 24;
//        contentStream.beginText();
//        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
//        contentStream.newLineAtOffset((page.getMediaBox().getWidth() - nameWidth) / 2, 600);
//        contentStream.showText(name);
//        contentStream.endText();
        // Set the font and font size
        // Write the content to the PDF
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 14);
        contentStream.newLineAtOffset(50, 500);
        contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 14);
        contentStream.showText(content);
        contentStream.endText();

        // Add the logo at the bottom left corner
        contentStream.drawImage(badgeImage, 50, 50, 150, 150);

        // Add the signature at the bottom right corner
        contentStream.drawImage(signatureImage, 400, 50, 150, 50);
        // Add the name of TuniTask at the bottom center
        contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 16);
        String organization = "TuniTask";
        float orgWidth = new PDType1Font(HELVETICA_BOLD).getStringWidth(organization) / 1000 * 16;
        contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 18);

        contentStream.beginText();
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() - orgWidth) / 2, 75);
        contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 18);
        contentStream.showText(organization);
        contentStream.endText();

        // Close the content stream and the document
        contentStream.close();
        document.save("certificate.pdf");
        document.close();

        return new File("certificate.pdf");
    }
    @FXML
    public void showCertificate() {
        // Retrieve the user's score from UserSession
        userSess us = userSess.getInstance();
        int score = us.getId_quiz().getScore();

        // Generate the PDF certificate
        String content = "Congratulations, you passed the quiz with a score of  " + score + " out of " + questionsTable.getItems().size() + " in the "+ us.getId_quiz().getQuiz_title()+" quiz!";
        try {
            File certificateFile = generatePdf(content);
            // Open the certificate file in the default application for viewing PDFs
            Desktop.getDesktop().open(certificateFile);

            // Provide a button for the user to download the file
//            Button downloadButton = new Button("Download Certificate");
//            downloadButton.setOnAction(event -> {
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Save Certificate");
//                fileChooser.setInitialFileName("certificate.pdf");
//                File saveFile = fileChooser.showSaveDialog(null);
//                if (saveFile != null) {
//                    try {
//                        Files.copy(certificateFile.toPath(), saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                        // Show an alert to indicate that the file has been downloaded
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Certificate downloaded successfully!", ButtonType.OK);
//                        alert.showAndWait();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Alert alert = new Alert(Alert.AlertType.ERROR, "Could not download the certificate!", ButtonType.OK);
//                        alert.showAndWait();
//                    }
//                }
//            });
//
//            // Show the button in an alert
//            Alert alert = new Alert(Alert.AlertType.NONE, "Your certificate is ready!", ButtonType.OK);
//            alert.setGraphic(downloadButton);
//            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not generate the certificate!", ButtonType.OK);
            alert.showAndWait();
        }
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
        // Retrieve the score from the UserSession instance that was used to store it
        int score = userSess.getId_quiz().getScore();

// Enable the certificate button if the score is equal to the number of questions
        if (score == questionsTable.getItems().size() && score != 0) {
            certificateButton.setVisible(true);
            certificateButton.setDisable(false);

        }
    }

    public void retourBtn(ActionEvent event) {
        Parent root;
        try {
            ( (Node) event.getSource()).getScene().getWindow().hide();
            root = FXMLLoader.load(getClass().getResource("Freelancer.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TuniTask");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TuniTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Add this method to listen for double-click events on the table rows
    public void initialize() {
        // Retrieve the user's score from UserSession
        userSess us = userSess.getInstance();
        int score = us.getId_quiz().getScore();

        // Disable the certificate button if the score is not equal to the number of questions
        if (score != questionsTable.getItems().size()) {
            certificateButton.setDisable(true);
        }
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
        userSess us = userSess.getInstance();
        us.setId_question(selectedQuestion);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.showAndWait();
    }




}
