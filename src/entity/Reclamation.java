/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hp
 */
public class Reclamation {
    private int id ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String description ;
    private String resolution ; 
   private int   id_user;
    public Reclamation (){}

    public Reclamation(int id) {
        this.id = id;
    }
    
    public Reclamation(int id, String nom, String prenom, String email, String description,int id_user) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
         this.id_user = id_user;
    }
    public Reclamation(int id, String nom, String prenom, String email, String description, String resolution,int id_user) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.resolution = resolution ; 
                 this.id_user = id_user;

    }

    public Reclamation(String nom, String prenom, String email, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", description=" + description + '}';
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
    
    
    
    
}
