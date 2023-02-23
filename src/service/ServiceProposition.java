/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entite.Demande;
import entite.Proposition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author mohamed gabsi
 */
public class ServiceProposition implements IServiceProposition<Proposition> { 
    
     private final Connection conn;
     private PreparedStatement pst;
     private ResultSet rs;

    public ServiceProposition() {
        this.conn = DataSource.getInstance().getCnx();
    }
    
     
     @Override
    public void insert(Proposition p) {
       String req = "INSERT INTO proposition (id_demande,id_freelancer) VALUES (?,?) ";
        try {
             pst = conn.prepareStatement(req);
            pst.setInt(1, p.getId_demande().getId());
            pst.setInt(2,p.getId_freelancer());
            pst.executeUpdate();
            System.out.println("Proposition ajoutée");
        } catch (SQLException ex) {
         Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void update(Proposition p, int id) {
        String req = "UPDATE proposition SET id_demande = ?, id_freelancer= ?  WHERE id = ?";
        try {

            pst = conn.prepareStatement(req);
            //ps.setInt(1,p.getId());
            //ps.setObject(1, p.getId_demande());
            pst.setInt(1, p.getId_demande().getId());
            pst.setInt(2, p.getId_freelancer());
            pst.setInt(3, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Override
    public void delete(int id) {
        String req = "DELETE FROM proposition WHERE id = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Proposition supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
@Override
public List<Proposition> readAll() {
    List<Proposition> list = new ArrayList<>();
    String requete = "SELECT * FROM proposition";
    try {
        Statement st = conn.createStatement();
        rs = st.executeQuery(requete);
        while (rs.next()) {
            int id = rs.getInt("id");
            int id_demande = rs.getInt("id_demande");
            int id_freelancer = rs.getInt("id_freelancer");

            Demande demande = new Demande();
            demande.setId(id_demande);

            Proposition p = new Proposition(id, demande, id_freelancer);
            list.add(p);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}
    
    @Override
     public Proposition readById(int id) {
        Proposition p = null;
String sql = "SELECT * FROM proposition WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql);){ 
            ps.setInt(1, id);
             rs = ps.executeQuery();

            if (rs.next()) {
                int id_demande = rs.getInt(2);
                int id_freelancer = rs.getInt(3);
                Demande demande = new Demande();
                 demande.setId(id_demande);

                p = new Proposition(id, demande, id_freelancer);
            }
        } catch (SQLException e) {
        Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, e);
        }

       return p;
    }
  

}
