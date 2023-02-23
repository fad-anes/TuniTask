/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Role;
import entite.Users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;

/**
 *
 * @author abdes
 */
public class ServiceUsers implements IService<Users> {
private Connection conn;

    public ServiceUsers() {
        conn = (Connection) DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(Users t) {
 String requete3 = "INSERT INTO users (password, email, first_name, last_name, date_of_birth,srcimage) VALUES (?,?,?,?,?,?)" ;
            try {
            PreparedStatement pst = conn.prepareStatement(requete3);
            pst.setString(1, t.getPassword());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getFirstName());
            pst.setString(4, t.getLastName());
            pst.setDate(5, t.getDateOfBirth());
            pst.setString(6,t.getSrcimage() );
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public void delete(Users t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Users t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List readAll() {
        
 String query = "SELECT * FROM users";
    List<Users> userList = new ArrayList<>();
      PreparedStatement pst;
    try {
        pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery(query); 
             while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("first_name");
        String pname = rs.getString("last_name");
        String email = rs.getString("email");
        Date date= rs.getDate("date_of_birth");
        Users user = new Users(id, email, name,pname,date);
        userList.add(user);
      }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
    }
      return userList ;
        }

    @Override
    public Users readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getIdByEmail(String s) {
      
        ArrayList <Users> list= (ArrayList)this.readAll() ;
       return list.stream().filter( (Users u) -> u.getEmail().equals(s)).mapToInt((Users u) -> u.getId()).findAny().orElse(-1) ; 
    
    
    }
    public String getPwdByEmail(String s) {
      
        ArrayList <Users> list= (ArrayList)this.readAll() ;
        System.out.println("(err");
       return list.stream().filter( (Users u) -> u.getEmail().equals(s)).map((Users u) -> u.getPassword()).findAny().orElse("") ; 
    
    
    }
       public  Map readAllUsersWithTheirRoleByJointure() {
        
 String query = "SELECT * FROM role INNER JOIN users ON role.id_user = users.id";
    Map<Users,Role> user_Rolemap = new HashMap<>();
      PreparedStatement pst;
    try {
        pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery(query); 
             while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("first_name");
        String pname = rs.getString("last_name");
        String email = rs.getString("email");
        Date date= rs.getDate("date_of_birth");
        Users user = new Users(id, email, name,pname,date);
        String role_name= rs.getString("role_name");
        Role role;
        switch (role_name) {
            case "Freelancer" :
           String skills= rs.getString("role_name");
           String experience=rs.getString("experience");
            role= new Role(role_name, skills, experience, user);
        break;
            case "Organisateur" :
           String entreprise= rs.getString("entreprise");
            role= new Role(role_name, entreprise, user);
           break;
            default: 
             role= new Role(role_name, user);   
        }
        user_Rolemap.put(user,role);
      }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
    }
      return user_Rolemap ;
        }
  
       /*public String getRoleByEmail(String s) {
       HashMap <Users,Role> m=(HashMap)readAllUsersWithTheirRoleByJointure() ;
       //Users u= m.keySet().stream().filter( (Users u) -> u.getEmail().equals(s)).findAny().getObject();
       String role ="";
       Users user ;
       for(Users u : m.keySet())
       {
           if ( u.getEmail().equals(s) )
               user=u ;
               role=user.getRoleCollection()
               
       }
       return role;
       }*/
}
