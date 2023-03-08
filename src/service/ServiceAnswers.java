package service;

import entite.Answers;
import entite.Questions;
import entite.Quizs;
import utile.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceAnswers implements IServiceAnswers {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceAnswers() {
        con = DataSource.getInstance().getCnx();
    }

@Override
    public void insert(Answers answer) {
        String req = "INSERT INTO answers (id_question, id_quiz, answer, is_correct) VALUES (?, ?, ?, ?)  ";
        //String req="INSERT INTO answers (id_question, id_quiz, answer, is_correct) VALUES (10, 1, 'isi', true)";
        try {
            ps = con.prepareStatement(req);
            ps.setInt(1, answer.getQuestion_id().getId());
            ps.setInt(2, answer.getQuiz_id().getId_quiz());
            ps.setString(3, answer.getAnswer());
            ps.setBoolean(4, answer.getIs_correct());
            ps.executeUpdate();
            System.out.println("Answer added successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnswers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void delete(Answers answer) {
        String req = "DELETE FROM answers WHERE id_answer = ?";
        try {
            ps = con.prepareStatement(req);
            ps.setInt(1, answer.getId_answer());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Row deleted successfully!");
            } else {
                System.out.println("No rows were deleted.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnswers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Answers answer, int id) {
        String req = "UPDATE answers SET id_answer=?,id_question=?,id_quiz=?, answer=?, is_correct=? WHERE id_answer=?";
        try {
            ps = con.prepareStatement(req);
            ps.setInt(1, answer.getId_answer());
            ps.setInt(2, answer.getQuestion_id().getId());
            ps.setInt(3, answer.getQuiz_id().getId_quiz());
            ps.setString(4, answer.getAnswer());
            ps.setBoolean(5, answer.getIs_correct());
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnswers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Answers> readAll() {
        List<Answers> answers = new ArrayList<>();
        String query = "SELECT * FROM answers JOIN questions ON answers.id_question = questions.id_question JOIN quizs ON answers.id_quiz = quizs.id_quiz";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_answer");
                int questionId = rs.getInt("id_question");
                String questionText = rs.getString("question_text");
                int quizId = rs.getInt("id_quiz");
                String quizTitle = rs.getString("quiz_title");
                String quizDescription = rs.getString("quiz_description");
                String answerText = rs.getString("answer");
                boolean isCorrect = rs.getBoolean("is_correct");
                Quizs quiz = new Quizs(quizId, quizTitle, quizDescription);
                Questions question = new Questions(questionId, questionText, quiz);
                Answers answer= new Answers(id, question, quiz, answerText, isCorrect);
                answers.add(answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnswers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }
    @Override
    public List<Answers> getAnswersByQuestion(Questions question) {
        List<Answers> answers = new ArrayList<>();
        String query = "SELECT * FROM answers JOIN questions ON answers.id_question = questions.id_question WHERE questions.id_question = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, question.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_answer");
                int questionId = rs.getInt("id_question");
                int quizId = rs.getInt("id_quiz");
                String questionText = rs.getString("question_text");
                String answerText = rs.getString("answer");
                boolean isCorrect = rs.getBoolean("is_correct");
                Quizs quiz = new Quizs(quizId);
                Questions question1 = new Questions(questionId, questionText, quiz);
                Answers answer = new Answers(id, question, quiz, answerText, isCorrect);
                answers.add(answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnswers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }

    public Answers getAnswerById(int id) {
        Answers answer = null;
        String query = "SELECT * FROM answers JOIN questions ON answers.id_question = questions.id_question JOIN quizs ON answers.id_quiz = quizs.id_quiz WHERE answers.id_answer = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int answerId = rs.getInt("id_answer");
                int questionId = rs.getInt("id_question");
                int quizId = rs.getInt("id_quiz");
                String questionText = rs.getString("question_text");
                String quizTitle = rs.getString("quiz_title");
                String quizDescription = rs.getString("quiz_description");
                String answerText = rs.getString("answer");
                boolean isCorrect = rs.getBoolean("is_correct");
                Quizs quiz = new Quizs(quizId, quizTitle, quizDescription);
                Questions question = new Questions(questionId, questionText, quiz);
                answer = new Answers(answerId, question, quiz, answerText, isCorrect);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnswers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    public List<Answers> readByQuestionId(int questionId) {
        List<Answers> answers = new ArrayList<>();
        String query = "SELECT * FROM answers JOIN questions ON answers.id_question = questions.id_question JOIN quizs ON answers.id_quiz = quizs.id_quiz WHERE questions.id_question = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_answer");
                int quizId = rs.getInt("id_quiz");
                String answerText = rs.getString("answer");
                boolean isCorrect = rs.getBoolean("is_correct");
                Quizs quiz = new Quizs(quizId);
                Questions question = new Questions(questionId);
                Answers answer = new Answers(id, question, quiz, answerText, isCorrect);
                answers.add(answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnswers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }



}
