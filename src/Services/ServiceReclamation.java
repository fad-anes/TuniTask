/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import cnx_DB.DataSource;
import entity.Reclamation;
import entity.Reponserec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class ServiceReclamation implements IserviceReclamation<Reclamation> {

    @Override
    public void ajouterReclamation(Reclamation r) {
        try {
            String requete= "INSERT INTO reclamation ( description,  nom,  prenom,  email,id_user)"
                    + "VALUES (?,?,?,?,?)";
            
          PreparedStatement pst = DataSource.getInstance().getCnx() //envoie d'une requete a la base
                 .prepareStatement(requete);
            pst.setString(1,r.getDescription());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getPrenom());
            pst.setString(4,r.getEmail());
             pst.setInt(5,r.getId_user());
            
            pst.executeUpdate();   
            System.out.println("Reclamation inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }
    @Override
    public void supprimerReclamation(Integer id) {
        try {
            System.out.println(id);
            
            String requete = "DELETE FROM reclamation where id="+id+"";
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
           // pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void modifierReclamation(Reclamation r) {
        try {
            
     String requete = "UPDATE reclamation SET  description=?,nom=?,prenom=?,email=?,id_user=? Where id =?";
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, r.getDescription());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getPrenom());
            pst.setString(4, r.getEmail()); 
            pst.setInt(5, r.getId_user());
            pst.setInt(6, r.getId());
            
            pst.executeUpdate();
            System.out.println("Reclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficherReclamations() {
         List<Reclamation> ReclamationsList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation r ";
            Statement st = DataSource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); //te5ou hajet f reclamation w thotohom f rs
            while(rs.next()){
                Reclamation r = new Reclamation();
                 r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setDescription(rs.getString("description"));
                r.setId_user(rs.getInt("id_user"));
                System.out.println("les reclamations sont" +r.toString());
                
                ReclamationsList.add(r);
               
            }
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return ReclamationsList;
         
         }
   public List<Reclamation> afficherReclamations2() {
         List<Reclamation> ReclamationsList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation r order by nom ";
            Statement st = DataSource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); //te5ou hajet f reclamation w thotohom f rs
            while(rs.next()){
                Reclamation r = new Reclamation();
                 r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setDescription(rs.getString("description"));
                 r.setId_user(rs.getInt("id_user"));
                System.out.println("les reclamations sont" +r.toString());
                
                ReclamationsList.add(r);
               
            }
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return ReclamationsList;
         
         }
    
    
}
