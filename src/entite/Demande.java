/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;
import java.sql.Timestamp;



/**
 *
 * @author mohamed gabsi
 */
public class Demande {
    private int id;
    private Users id_client;
    private String nom;
    private String prenom;
    private String email;
    private String titre;
    private String description;
    private String salaire;
    private String delai;
    private String langage;
    private Timestamp created_at;

    public Demande() {
    }

    public Demande(int id, Users id_client, String nom, String prenom, String email, String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.id = id;
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    public Demande(int id, String nom, String prenom, String email, String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    public Demande(Users id_client, String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.id_client = id_client;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    
    public Demande(String nom, String prenom, String email, String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    public Demande(int id, Users id_client, String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.id = id;
        this.id_client = id_client;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    public Demande(int id, String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    public Demande(String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    
    public Demande(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Demande(int id) {
        this.id = id;
    }

   
    public int getId() {
        return id;
    }

    public Users getId_client() {
        return id_client;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getSalaire() {
        return salaire;
    }

    public String getDelai() {
        return delai;
    }

    public String getLangage() {
        return langage;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_client(Users id_client) {
        this.id_client = id_client;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    //@Override
   // public String toString() {
       // return "Demande{" + "id=" + id + ", client_id=" + id_client + ", titre=" + titre + ", description=" + description + ", salaire=" + salaire + ", delai=" + delai + ", langage=" + langage + ", created_at=" + created_at + '}';
    //}
 //@Override
   // public String toString() {
     //   return "Demande{" + "id=" + id  + ", titre=" + titre + ", description=" + description + ", salaire=" + salaire + ", delai=" + delai + ", langage=" + langage + ", created_at=" + created_at + '}';
    //}

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", titre=" + titre + ", description=" + description + ", salaire=" + salaire + ", delai=" + delai + ", langage=" + langage + ", created_at=" + created_at + '}';
    }
 
}
