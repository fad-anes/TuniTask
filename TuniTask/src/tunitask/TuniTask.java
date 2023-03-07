/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunitask;

import entite.PasswordHasher;
import entite.Role;
import entite.Users;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ServiceRole;
import service.ServiceUsers;
import utile.DataSource;

/**
 *
 * @author abdes
 */
import org.mindrot.jbcrypt.BCrypt;

public class TuniTask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         //DataSource ds1 =DataSource.getInstance();
         //ServiceRole s = new ServiceRole();
        // ServiceUsers r = new ServiceUsers();
         //System.out.println(r.readById(8));
         //PasswordHasher ph=new PasswordHasher();
        // String sh= ph.hashPassword("ok");
         //String sh1= ph.hashPassword("ok");
         String password = "mypassword";
         String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
         if (BCrypt.checkpw(password, hashedPassword)) {
    System.out.println("Mot de passe valide !");
} else {
    System.out.println("Mot de passe invalide !");
}
         System.out.println(hashedPassword);
         
      // Users us= new  Users( 12,"update", "email", "update", "lastName", java.sql.Date.valueOf(LocalDate.parse("2019-03-29")),"src");
     // Users us= new Users(8);
     // r.Desactiver(r.readById(8));
      //r.update(us);
            
   
       //r.insert(us);
        //System.out.println(us.getFirstName().substring(0,2).length());
       /* ArrayList <Users> list=(ArrayList)r.readAll() ;
        ArrayList <Users> list1=(ArrayList)list.stream().filter((Users u) -> u.getFirstName().length()>= "a".length())
                .filter(u -> u.getFirstName().substring(0,"a".length()).equals("a") )
                .collect(Collectors.toList());
        System.out.println(list1);*/
        //System.out.println(list);
       //int a=list.stream().filter( (Users u) -> u.getEmail().equals("abd@gmail.com")).mapToInt((Users u) -> u.getId()).findAny().orElse(-3) ;
       // System.out.println(a);
      //System.out.println(r.readAllUsersWithTheirRoleByJointure());
        /*System.out.println(list.stream().filter( (Users u) ->{ /*u.getEmail().equals("a@gmail.com");
                System.out.println(u.getPassword()); 
                return u.getEmail().equals("a@gmail.com");
                }).map((Users u) -> u.getPassword()).findAny().orElse("ok") ); */
        //Role role= new Role( "Client" , us);
        //System.out.println(r.getPwdByEmail("abd@gmail.com"));
        // System.out.println(r.readAllUsersWithTheirRoleByJointure());
        //s.insert(role);
         //s.delete(r);
         
         //r.insert(r);
         //ArrayList a= (ArrayList) s.readAll();
         //a.forEach(e -> System.out.println(e.toString()));
         System.out.println("test");
         //System.out.println(r.filtrer_users_By_Role("Admin"));
         //System.out.println(s.Role_name_By_Id_user(8));
    }
    
}
