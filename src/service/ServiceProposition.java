/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.Demande;
import entity.Proposition;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyDB;
/**
 *
 * @author mohamed gabsi
 */
public class ServiceProposition implements IServiceProposition<Proposition> { 
    
     private final Connection conn;
     private PreparedStatement pst;
     private ResultSet rs;
     public static Users u=new Users(3,"ali","moussa","alimoussa@gmail.com");
//public static Users u=new Users(1,"amine","gabsi","gabsiamine@gmail.com");

    public ServiceProposition() {
        this.conn = MyDB.getInstance().getConnection();
    }
    
     
     @Override
    public void insert(Proposition p) {
       String req = "INSERT INTO proposition (id_demande,id_freelancer) VALUES (?,?) ";
        try {
             pst = conn.prepareStatement(req);
            pst.setInt(1, p.getId_demande());
            pst.setInt(2,u.getId());
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
            pst.setInt(1, p.getId_demande());
            pst.setInt(2, u.getId());
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
  
//@Override
//public List<Proposition> readAll() {
    //List<Proposition> list = new ArrayList<>();
    //String requete = "SELECT * FROM proposition";
    //try {
     //   Statement st = conn.createStatement();
       // rs = st.executeQuery(requete);
        //while (rs.next()) {
         //   int id = rs.getInt("id");
          //  int id_demande = rs.getInt("id_demande");
          //  int id_freelancer = rs.getInt("id_freelancer");

          //  Demande demande = new Demande();
           // demande.setId(id_demande);

          //  Proposition p = new Proposition(id, demande, id_freelancer);
          //  list.add(p);
        //}
    //} catch (SQLException ex) {
     //   Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
   // }
   // return list;
//}
   // @Override
    //public List<Proposition> readAll() {
       // List<Proposition> list=new ArrayList<>();
       // String requete="select p.id,d.id,d.titre,u.first_name,u.last_name from proposition AS p" +
       //       " JOIN demande AS d ON p.id_demande=d.id "+"JOIN users AS u ON p.id_freelancer =u.id";
      //  try {
           // Statement st=conn.createStatement();
          //   rs=st.executeQuery(requete);
          //  while(rs.next()){
                //Proposition p=new Proposition(rs.getInt("id"),rs.getInt("id_demande"),
                  //      rs.getString("first_name"),rs.getString("last_name"),
                 //       rs.getString("titre"));
             //   list.add(p);
           // }

       // } catch (SQLException ex) {
            //.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
       // }
       // return list;
   // }
    //@Override
//public List<Proposition> readAll() {
   // List<Proposition> list=new ArrayList<>();
    ////String requete="select p.id,d.id,d.titre,u.first_name,u.last_name from proposition AS p" +
       //   " JOIN demande AS d ON p.id_demande=d.id "+"JOIN users AS u ON p.id_freelancer =u.id";
   // try {
      //  Statement st=conn.createStatement();
        //rs=st.executeQuery(requete);
       // while(rs.next()){
           // Demande demande = new Demande();
          //  demande.setId(rs.getInt("id_demande"));
           // demande.setTitre(rs.getString("titre"));
         //   Users users = new Users();
         //   users.setFirstName(rs.getString("first_name"));
         //   users.setLastName(rs.getString("last_name"));
           // Proposition p=new Proposition(rs.getInt("id"), demande,users.getFirstName(), users.getLastName(), demande.getTitre());
          //  list.add(p);
       // }
   // } catch (SQLException ex) {
       // Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
   // }
   /// return list;
//}
    
   @Override
public List<Proposition> readAll() {
    List<Proposition> propositions = new ArrayList<>();
    String sql = "SELECT p.id, p.id_demande, u.first_name, u.last_name, u.email "
            + "FROM proposition AS p "
            + "JOIN users AS u ON p.id_freelancer = u.id";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
         rs = ps.executeQuery();

        while (rs.next()) {
            int propositionId = rs.getInt("id");
            int demandeId = rs.getInt("id_demande");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            Demande demande = new Demande(demandeId);
            Users users = new Users();
            users.setFirstName(firstName);
            users.setLastName(lastName);
            users.setEmail(email);
            Proposition p = new Proposition(propositionId, demande.getId(), users.getFirstName(), users.getLastName(), users.getEmail());
            propositions.add(p);
        }
    } catch (SQLException e) {
        Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, e);
    }

    return propositions;
}

 @Override
public Proposition readById(int id) {
    Proposition p = null;
    String sql = "SELECT p.id, p.id_demande , u.first_name, u.last_name, u.email "
            + "FROM proposition AS p "
            + "JOIN users AS u ON p.id_freelancer = u.id "
            + "WHERE p.id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
         rs = ps.executeQuery();

        if (rs.next()) {
            int propositionId = rs.getInt("id");
            int demandeId = rs.getInt("id_demande");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            Demande demande = new Demande(demandeId);
            Users users = new Users();
            users.setFirstName(firstName);
            users.setLastName(lastName);
            users.setEmail(email);
            p = new Proposition(propositionId, demande.getId(), users.getFirstName(), users.getLastName(), users.getEmail());
        }
    } catch (SQLException e) {
        Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, e);
    }

    return p;
}

}
