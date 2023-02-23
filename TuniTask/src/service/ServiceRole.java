/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Role;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;
import utile.DataSource;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author abdes
 */
public class ServiceRole implements IService<Role> {
private Connection conn;

    public ServiceRole() {
        conn = (Connection) DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(Role t) {
        ServiceUsers su = new ServiceUsers();
        switch(t.getRoleName()) {
  case "Client":
      String requete = "INSERT INTO role(role_name,id_user) VALUES (?,?)" ;
            try {
              
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getRoleName());
            pst.setInt( 2, su.getIdByEmail(t.getIdUser().getEmail()));
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    break;
  case "Freelancer":
    String requete1 = "INSERT INTO role(role_name,skills,experience,id_user) VALUES (?,?,?,?)" ;
            try {
            PreparedStatement pst = conn.prepareStatement(requete1);
            pst.setString(1, t.getRoleName());
            pst.setString(2, t.getSkills());
            pst.setString(3, t.getExperience());
            pst.setInt(4,su.getIdByEmail(t.getIdUser().getEmail()));
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    break;
     case "Organizateur":
    String requete2 = "INSERT INTO role(role_name, entreprise,id_user) VALUES (?,?,?)" ;
            try {
            PreparedStatement pst = conn.prepareStatement(requete2);
            pst.setString(1, "Organizateur");
            pst.setString(2, t.getEntreprise());
            pst.setInt(3,su.getIdByEmail(t.getIdUser().getEmail()));
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    break;
  default:
    String requete3 = "INSERT INTO role(role_name,id_user) VALUES (?,?)" ;
            try {
            PreparedStatement pst = conn.prepareStatement(requete3);
            pst.setString(1, "Admin");
            pst.setInt(2,su.getIdByEmail(t.getIdUser().getEmail()));
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
}
  
        
      
        
        
    }

    @Override
    public void delete(Role t) {
       String requete="DELETE FROM role WHERE id_role=" + t.getIdRole();
       try {
         Statement st = conn.createStatement();
            st.executeUpdate(requete);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Role t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
      public List<Role> readAll() {
            List<Role> list=new ArrayList<>();
           /* String requete="select * from role";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Role p=new Role(rs.getInt("id_role"), rs.getString("role_name"),
                        rs.getString("skills"), rs.getString("experience"),rs.getString("entreprise"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return list;          
    }

    @Override
    public Role readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
