package Service;

import Entity.Questions;
import Entity.Quizs;
import UTILS.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceQuestions implements IServiceQuestions<Questions>{
    private Connection con;
    private Statement ste;

    public ServiceQuestions() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Questions questions) {
        String req = "INSERT INTO questions (question_text,id_quiz) VALUES ( ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, questions.getQuestion_text());
            ps.setInt(2, questions.quiz().getId_quiz());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Questions questions) {
        String req = "DELETE  FROM questions WHERE id_question = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, questions.getId_question());
            int rowsDeleted=ps.executeUpdate();
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
        String req = "UPDATE questions SET id_question=? ,question_text = ?, id_quiz= ?  WHERE id_question = ?";
        try {

            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, Q.getId_question());
            ps.setString(2, Q.getQuestion_text());
            ps.setInt(3, Q.quiz().getId_quiz());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Questions> readAll() {
        String req = "SELECT * FROM questions";
        List<Questions> list = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                int id_question = rs.getInt("id_question");
                String question_text = rs.getString("question_text");
                int id_quiz = rs.getInt("id_quiz");

                list.add(new Questions(id_question, question_text, new Quizs(id_quiz)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Questions readById(int id) {
        String req = "SELECT * FROM questions WHERE id_question = ?";
        Questions q = null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
             int   id_question = rs.getInt("id_question");
                String question_text = rs.getString("question_text");
                int id_quiz = rs.getInt("id_quiz");
                q = new Questions(id_question, question_text, new Quizs(id_quiz));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
}
