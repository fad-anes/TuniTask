package service;

import entity.Questions;
import entity.Quizs;
import entity.Reponses;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicesReponses implements IServiceReponses<Reponses> {

    private Connection con;
    private Statement ste;

    public ServicesReponses() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reponses reponses) {
        String req = "INSERT INTO reponses (reponse_text, is_correct, id_question) VALUES ( ?,?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, reponses.getReponse_text());
            ps.setBoolean(2, reponses.is_correct());
            ps.setInt(3, reponses.getId_question().getId_question());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Reponses reponses) {
        String req = "DELETE  FROM reponses WHERE id_reponse = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, reponses.getId_reponse());
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
    public void update(Reponses reponses, int id) {
        String req = "UPDATE reponses SET id_reponse=?, reponse_text=?, is_correct=? ,id_question=? WHERE id_reponse = ?";
        try {

            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, reponses.getId_reponse());
            ps.setString(2, reponses.getReponse_text());
            ps.setBoolean(3, reponses.is_correct());
            ps.setInt(4, reponses.getId_question().getId_question());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reponses> readAll() {
        String req = "SELECT * FROM reponses";
        List<Reponses> list = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {

                int id_reponse = rs.getInt("id_reponse");
                String reponse_text = rs.getString("reponse_text");
                boolean is_correct = rs.getBoolean("is_correct");
                int id_question = (int) rs.getObject("id_question");
              list.add(new Reponses(id_reponse, reponse_text, is_correct,new Questions(id_question)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Reponses readById(int id) {
        String req = "SELECT * FROM reponses WHERE id_reponse = ?";
        Reponses rep = null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int  id_reponse = rs.getInt("id_reponse");
                String reponse_text = rs.getString("reponse_text");
                boolean is_correct = rs.getBoolean("is_correct");
                int id_question = (int) rs.getObject("id_question");
                rep = new Reponses(id_reponse, reponse_text, is_correct,new Questions(id_question));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuizs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
}
