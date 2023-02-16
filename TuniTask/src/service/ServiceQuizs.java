package service;

import entity.Quizs;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceQuizs implements  IService<Quizs>{
    private Connection con;
    public ServiceQuizs() {
con = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Quizs t) {
        String query = "INSERT INTO quizs (quiz_title, quiz_description) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, t.getQuiz_title());
            ps.setString(2, t.getQuiz_description());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Quizs quiz) {
        try {
            String requete = "DELETE FROM quizs WHERE quiz_id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, quiz.getQuiz_id());
            pst.executeUpdate();
            System.out.println("Quiz deleted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Quizs quiz) {
        try {
            String requete = "UPDATE quizs SET quiz_title=?, quiz_description=? WHERE quiz_id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, quiz.getQuiz_id());
            pst.setString(2, quiz.getQuiz_title());
            pst.setString(3, quiz.getQuiz_description());
            pst.executeUpdate();
            System.out.println("Quiz updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Quizs> readAll() {
        List<Quizs> quizs = new ArrayList<>();
        try {
            String requete = "SELECT * FROM quizs";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                int quiz_id = rs.getInt("quiz_id");
                String quiz_title = rs.getString("quiz_title");
                String quiz_description = rs.getString("quiz_description");
                Quizs quiz = new Quizs(quiz_id, quiz_title, quiz_description);
                quizs.add(quiz);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quizs;
    }

    @Override
    public Quizs readById(int id) {
        Quizs quiz = null;
        try {
            String requete = "SELECT * FROM quizs WHERE quiz_id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int quiz_id = rs.getInt("quiz_id");
                String quiz_title = rs.getString("quiz_title");
                String quiz_description = rs.getString("quiz_description");
                quiz = new Quizs(quiz_id, quiz_title, quiz_description);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quiz;
    }
}
