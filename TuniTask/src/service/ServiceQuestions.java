package service;

import entity.Questions;
import entity.Quizs;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceQuestions implements IServiceQuestions<Questions> {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceQuestions() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Questions questions) {
        String req = "INSERT INTO questions (question_text, id_quiz) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(req);
            ps.setString(1, questions.getQuestion());
            ps.setInt(2, questions.getQuiz().getId_quiz());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Questions questions) {
        String req = "DELETE FROM questions WHERE question_text = ?";
        try {
            ps = con.prepareStatement(req);
            ps.setString(1, questions.getQuestion());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Row deleted successfully!");
            } else {
                System.out.println("No rows were deleted.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Questions Q, int id) {
        String req = "UPDATE questions SET id_question=?, question_text=?, id_quiz=? WHERE id_question=?";
        try {
            ps = con.prepareStatement(req);
            ps.setInt(1, Q.getId());
            ps.setString(2, Q.getQuestion());
            ps.setInt(3, Q.getQuiz().getId_quiz());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Questions> readAll() {
        List<Questions> questions = new ArrayList<>();
        String query = "SELECT * FROM questions JOIN quizs ON questions.id_quiz = quizs.id_quiz";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_question");
                String questionText = rs.getString("question_text");
                int quizId = rs.getInt("id_quiz");
                String quizTitle = rs.getString("quiz_title");
                String quizDescription = rs.getString("quiz_description");
                Quizs quiz = new Quizs(quizId, quizTitle, quizDescription);
                Questions question = new Questions(id, questionText, quiz);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return questions;
    }

    @Override
    public List<Questions> readByQuizId(int quizId) {
        List<Questions> questions = new ArrayList<>();
        String query = "SELECT * FROM questions JOIN quizs ON questions.id_quiz = quizs.id_quiz WHERE quizs.id_quiz = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, quizId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_question");
                String questionText = rs.getString("question_text");
                String quizTitle = rs.getString("quiz_title");
String quizDescription = rs.getString("quiz_description");
                Quizs quiz = new Quizs(quizId, quizTitle, quizDescription);
                Questions question = new Questions(id, questionText,quiz);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return questions;
    }
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}