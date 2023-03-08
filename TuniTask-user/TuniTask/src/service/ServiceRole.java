/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Role;
import entite.Users;
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
    public void update(Role t) {
        if (t.getRoleName().equals("Freelancer"))
        {
           String requete = "UPDATE role SET skills=?,experience=?,langage_de_programmation=? WHERE id_role=? " ;
              try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getSkills());
            pst.setString(2, t.getExperience());
            pst.setString(3, t.getLangage());
            pst.setInt(4,t.getIdRole());
           
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
               
                if (t.getRoleName().equals("Organizateur"))
                {
    String requete2 = "UPDATE role SET entreprise= ? WHERE id_role=? " ;
            try {
            PreparedStatement pst = conn.prepareStatement(requete2);
            pst.setString(1,t.getEntreprise());
            pst.setInt(2, t.getIdRole());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
                }      
           
        }
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
    String requete1 = "INSERT INTO role(role_name,skills,experience,id_user,langage_de_programmation) VALUES (?,?,?,?,?)" ;
            try {
            PreparedStatement pst = conn.prepareStatement(requete1);
            pst.setString(1, t.getRoleName());
            pst.setString(2, t.getSkills());
            pst.setString(3, t.getExperience());
            pst.setInt(4,su.getIdByEmail(t.getIdUser().getEmail()));
            pst.setString(5, t.getLangage());
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
      public List<Role> readAll() {
               String query = "SELECT * FROM role";
    List<Role> roleList = new ArrayList<>();
  
      PreparedStatement pst;
    try {
        pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery(query); 
             while (rs.next()) {
        int id = rs.getInt("id_role");
        String name = rs.getString("role_name");
        String skills = rs.getString("skills");
        String experience = rs.getString("experience");
        String entreprise = rs.getString("entreprise");
        int id_user = rs.getInt("id_user");
        //Users user = new Users(id, email, name,pname,date);
        Role role = new  Role(id,name,skills,experience,entreprise,new Users(id_user));
         roleList.add(role);
      }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
    }
      return roleList ;    
               
    }

    @Override
    public Role readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          public Role Role_By_Id_user(int id)
    { 
         
         String query = "SELECT * FROM role  WHERE id_user="+id;
         PreparedStatement  pst;
         Role role=new Role();
    try {
         pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery(query); 
         while (rs.next()) {
        int idr = rs.getInt("id_role");
        String name = rs.getString("role_name");
        String skills = rs.getString("skills");
        String experience = rs.getString("experience");
        String langage = rs.getString("langage_de_programmation");
        String entreprise = rs.getString("entreprise");
        
        int id_user = rs.getInt("id_user");
        //Users user = new Users(id, email, name,pname,date);
        role = new  Role(idr,name,skills,experience,entreprise,new Users(id_user),langage);
    }} catch (SQLException ex) {
        Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
    }
         
         return role ;    
         //return list.stream().filter( (Users u) -> u.getEmail().equals(s)).map((Users u) -> u.getPassword()).findAny().orElse("") ; 
    }
          

}
