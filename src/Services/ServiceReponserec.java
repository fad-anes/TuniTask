/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import cnx_DB.DataSource;
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
public class ServiceReponserec implements IserviceReponserec <Reponserec>{

    @Override
    public void ajouterReponserec(Reponserec r) {
          try {
            String requete= "INSERT INTO reponserec (id,nomAgent,prenomAgent,resolution)"
                    + "VALUES (?,?,?,?)";
            
          PreparedStatement pst = DataSource.getInstance().getCnx() 
                 .prepareStatement(requete);
            pst.setInt(1,r.getId());
          pst.setString(2,r.getNomAgent());
            pst.setString(3, r.getPrenomAgent());
            pst.setString(4, r.getResolution());
        
            pst.executeUpdate();   
            System.out.println("Réponse inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
 }
    }

    @Override
    public void supprimerReponserec(Integer id) {
        try {
            System.out.println(id);
            String requete = "DELETE FROM reponserec where id="+id;
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
           // pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Réponse supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void modifierReponserec(Reponserec r) {
        try {
            String requete = "UPDATE reponserec SET nomAgent=?,prenomAgent=?,resolution=? where id=?";
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
            
            pst.setString(1,r.getNomAgent());
             pst.setString(2,r.getPrenomAgent());
            pst.setString(3,r.getResolution());
            pst.setInt(4,r.getId());
            
            pst.executeUpdate();
            System.out.println("Réponse modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    @Override
    public List afficherReponserec() {
        List<Reponserec> ReponserecList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reponserec r ";
            Statement st = DataSource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); //te5ou hajet f reclamation w thotohom f rs
            while(rs.next()){
                Reponserec r = new Reponserec();
                
                r.setId(rs.getInt("id"));
                r.setNomAgent(rs.getString("nomAgent"));
                r.setPrenomAgent(rs.getString("prenomAgent"));
                r.setResolution(rs.getString("resolution"));
                ReponserecList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReponserecList;
         
         }
    
    
     @Override
    public String getReponceById(Integer id) {
        List<Reponserec> ReponserecList = new ArrayList<>();
        String r="";
        try {
            String requete = "SELECT r.resolution FROM reponserec r where id =="+id+"";
            Statement st = DataSource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); //te5ou hajet f reclamation w thotohom f rs
            while(rs.next()){        
                r = rs.getString("resolution");     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
         
         }
    
    }


   