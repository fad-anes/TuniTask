/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import entite.Demande;
import java.util.List;
import java.sql.*;
//import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

//import java.sql.Date;
/**
 *
 * @author mohamed gabsi
 */
 
public class ServiceDemande implements IService<Demande> {
    
     private final Connection conn;
    

    public ServiceDemande() {
        this.conn = DataSource.getInstance().getCnx();
    }
    
     @Override
    public void insert(Demande d) {
       String req = "INSERT INTO demande (id_client,titre,description,salaire,delai,langage,created_at) VALUES (?,?,?,?,?,?,?) ";
        //pour executer req Statement et PreparedStatement
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setInt(1, d.getId_client());
            pst.setString(2,d.getTitre());
            pst.setString(3,d.getDescription());
            pst.setString(4,d.getSalaire());
            pst.setString(5, d.getDelai());
            pst.setString(6,d.getLangage());
            pst.setTimestamp(7, d.getCreated_at());
           
            pst.executeUpdate();
            System.out.println("Demande ajoutée");
        } catch (SQLException ex) {
         Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


  //@Override
   // public void update(Demande d) {
        //String requete =
              //  " UPDATE demande SET " + "titre=?," +
                     //   " description =?,salaire=?,delai=?,langage=?,created_at=? WHERE id_client= " + d.getId_client();
          //try {
           //PreparedStatement pst = conn.prepareStatement(requete);
           // pst.setString(1, d.getTitre());
           // pst.setString(2, d.getDescription());
           // pst.setString(3, d.getSalaire());
           // pst.setString(4, d.getDelai());
            //pst.setString(5, d.getLangage());
           // pst.setTimestamp(6, d.getCreated_at());
           // pst.executeUpdate();
       // } catch (SQLException ex) {
           //Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        //}
    //}
     
    @Override
    public void delete(Demande d) {
        String requete = "DELETE FROM demande WHERE id=" + d.getId();
        try {
            Statement st = conn.createStatement();
            st.execute(requete);
            System.out.println("demande supprimé !");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  
    @Override
    public List<Demande> readAll() {
            List<Demande> list=new ArrayList<>();
            String requete="select * from demande";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Demande d =new Demande(rs.getInt("id"), rs.getInt("id_client"), rs.getString("titre"), rs.getString("description"), rs.getString("salaire"), rs.getString("delai"), rs.getString("langage"), rs.getTimestamp("created_at"));
                list.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;          
    }
   

    @Override
     public Demande readById(int id) {
        Demande d = null;
String sql = "SELECT * FROM demande WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql);){ 
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                int id_client = rs.getInt(2);
                String titre = rs.getString(3);
                String description = rs.getString(4);
                String salaire = rs.getString(5);
                String delai = rs.getString(6);
                String langage = rs.getString(7); 
                Timestamp createdAt = rs.getTimestamp(8);

                d = new Demande(id, id_client, titre, description, salaire, delai, langage, createdAt);
            }
        } catch (SQLException e) {
          // e.printStackTrace();
        Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, e);
        }

       return d;
    }
 
     
     //@Override
   // public void update(Demande d) {
        //String requete =
                //" UPDATE demande SET " + "titre=?," +
                       // " description =?,salaire=?,delai=?,langage=?,created_at=? WHERE id_client= " + d.getId_client();
       // try {
           // PreparedStatement pst = conn.prepareStatement(requete);
            //pst.setString(1, d.getTitre());
            //pst.setString(2, d.getDescription());
            //pst.setString(3, d.getSalaire());
           // pst.setString(4, d.getDelai());
            //pst.setString(5, d.getLangage());
            //pst.setTimestamp(6, d.getCreated_at());
           // pst.executeUpdate();
        //} catch (SQLException ex) {
            //Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        //}
    //}

     
     @Override
public void update(Demande d, int id) {
    String req = "UPDATE demande SET " +
            "id_client=?, titre=?, description=?, salaire=?, delai=?, langage=?, created_at=? " +
            "WHERE id=?";
    try {
        PreparedStatement pst = conn.prepareStatement(req);  
        pst.setInt(1, d.getId_client());
        pst.setString(2, d.getTitre());
        pst.setString(3, d.getDescription());
        pst.setString(4, d.getSalaire());
        pst.setString(5, d.getDelai());
        pst.setString(6, d.getLangage());
        pst.setTimestamp(7, d.getCreated_at());
        pst.setInt(8, id);
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceProposition.class.getName()).log(Level.SEVERE, null, ex);
    }
}






    }
 
