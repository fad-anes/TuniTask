/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunitask;

import entite.Role;
import entite.Users;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import service.ServiceRole;
import service.ServiceUsers;
import utile.DataSource;

/**
 *
 * @author abdes
 */
public class TuniTask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         //DataSource ds1 =DataSource.getInstance();
         ServiceRole s = new ServiceRole();
         ServiceUsers r = new ServiceUsers();
        Users us= new  Users( "password", "email", "firstName", "lastName", java.sql.Date.valueOf(LocalDate.parse("2019-03-29")));
        //r.insert(us);
        ArrayList <Users> list= (ArrayList)r.readAll() ;
       int a=list.stream().filter( (Users u) -> u.getEmail().equals("abd@gmail.com")).mapToInt((Users u) -> u.getId()).findAny().orElse(-3) ;
        System.out.println(a);
       
       Role role= new Role( "Client" , us);
         
        //s.insert(role);
         //s.delete(r);
         
         //r.insert(r);
         //ArrayList a= (ArrayList) s.readAll();
         //a.forEach(e -> System.out.println(e.toString()));
    }
    
}
