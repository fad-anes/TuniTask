/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author mohamed gabsi
 */
public class Proposition {
    private int id;
    private int id_demande;
    private int id_freelancer;
    private String nom;
    private String prenom;
    private String email;

    public Proposition() {
    }

    public Proposition(int id, int id_demande, int id_freelancer) {
        this.id = id;
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
    }

    public Proposition(int id_demande, int id_freelancer) {
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
    }

    public Proposition(int id, int id_demande, String nom, String prenom, String email) {
        this.id = id;
        this.id_demande = id_demande;
        this.nom= nom;
        this.prenom= prenom;
        this.email=email;
    }

    public Proposition(int id, int id_demande, int id_freelancer, String nom, String prenom, String email) {
        this.id = id;
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    

    
    //public Proposition(Demande id_demande, int id_freelancer, Demande titre, Users firstName, Users lastName) {
      //  this.id_demande = id_demande;
       // this.id_freelancer = id_freelancer;
        //this.titre = titre;
        //this.firstName= firstName;
        //this.lastName= lastName;
    //}

   // public Proposition(int id, Demande id_demande,Demande titre, Users firstName, Users lastName) {
     //   this.id = id;
       // this.id_demande = id_demande;
         // this.titre = titre;
        //this.firstName = firstName;
        //this.lastName = lastName;
     
    //}

    //public Proposition(int id, Demande id_demande, Users firstName, Users lastName) {
      //  this.id = id;
        //this.id_demande = id_demande;
        //this.firstName = firstName;
        //this.lastName = lastName;
    //}

    
   

   // @Override
    //public String toString() {
       // return "Proposition{" + "id=" + id + ", id_demande=" + id_demande.getId() + ", id_freelancer=" + id_freelancer + '}';
    //}

   // @Override
    //public String toString() {
      //  return "Proposition{" + "id=" + id + ", id_demande=" + id_demande.getId() + ", id_freelancer=" + id_freelancer + ", titre=" + titre.getTitre() + '}';
    //}

   // @Override
    //public String toString() {
     //   return "Proposition{" + "id=" + id + ", id_demande=" + id_demande + ", firstName=" + firstName + ", lastName=" + lastName + ", titre=" + titre + '}';
    //}

    //@Override
    //public String toString() {
     //   return "Proposition{" + "id=" + id + ", id_demande=" + id_demande + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    //}

    //@Override
    //public String toString() {
      //  return "Proposition{" + "id=" + id + ", id_demande=" + id_demande + ", titre=" + titre + ", firstName=" + firstName + ", lastName=" + lastName  + '}';
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Proposition{" + "id=" + id + ", id_demande=" + id_demande + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }

    

}  
