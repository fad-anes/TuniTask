package service;

import entite.Quizs;

import utile.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceQuizs implements IServiceQuizs<Quizs> {

    private Connection con;
    private Statement ste;

    public ServiceQuizs() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Quizs q) {
        String req = "INSERT INTO quizs (quiz_title, quiz_description,score) VALUES ( ?,?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, q.getQuiz_title());
            ps.setString(2, q.getQuiz_description());
            ps.setInt(3, q.getScore());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Quizs q) {
        String req = "DELETE FROM quizs WHERE id_quiz = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, q.getId_quiz());
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
    public void update(Quizs q ,int id) {
        String req = "UPDATE quizs SET id_quiz=? ,quiz_title = ?, quiz_description= ? , score=? WHERE id_quiz = ?";
        try {

            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, q.getId_quiz());
            ps.setString(2, q.getQuiz_title());
            ps.setString(3, q.getQuiz_description());
            ps.setInt(4, q.getScore());
            ps.setInt(5, id);


                ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateQuizScore(int id, int newScore) {
        String req = "UPDATE quizs SET score=? WHERE id_quiz = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, newScore);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Quizs> readAll() {
        String req = "SELECT DISTINCT * FROM quizs";
        List<Quizs> list = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String desc = rs.getString(3);
                int score = rs.getInt(4);

                list.add(new Quizs(id, title, desc,score));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Quizs readById(int id) {
        String req = "SELECT * FROM quizs WHERE id_quiz = ?";
        Quizs q = null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String title = rs.getString(2);
                String desc = rs.getString(3);
                int score = rs.getInt(4);

                q = new Quizs(id, title, desc,score);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
}

