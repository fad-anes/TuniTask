/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestiondemande;
import entite.Proposition;
import entite.Demande;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;
//import java.sql.Date;
import service.ServiceDemande;
import service.ServiceProposition;
import utils.DataSource;
/**
 *
 * @author mohamed gabsi
 */
public class GestionDemande {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource ds1 =DataSource.getInstance();
       System.out.println(ds1);
        //LocalDateTime now = LocalDateTime.now();
        //Timestamp ls = Timestamp.valueOf(now);
        //Demande d=new Demande(5,"sanaaaaa","yoyo", "bb", "cc","dd", ls);
        //ServiceDemande sd=new ServiceDemande();
      
        Demande d = new Demande();
        d.setId(4);
        Proposition p=new Proposition(d,5);
        ServiceProposition sp=new ServiceProposition();
      
       
         //Proposition p=new Proposition();
         //ServiceProposition sp=new ServiceProposition();
       
      //sd.insert(d);
      //sd.update(d);
      //sd.update(d,28);
       //sd.delete(d);
       //System.out.println(sd.readById(20));
 //sd.readAll().forEach(System.out::println);
      

//sp.insert(p);
       //sp.update(p,15);
       //p.setId(15);
       //sp.delete(16);
       sp.readAll().forEach(System.out::println);
       //System.out.println(sp.readById(20));
     
     

    
    }
    
}
