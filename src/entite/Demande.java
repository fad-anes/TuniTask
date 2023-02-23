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
    private int id_client;
    private String titre;
    //private LocalDate date;
    private String description;
    private String salaire;
    private String delai;
    private String langage;
    private Timestamp created_at;

    public Demande() {
    }

    public Demande(int id, int id_client, String titre,String description,String salaire,String delai,String langage,Timestamp created_at) {
        this.id = id;
        this.id_client = id_client;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    public Demande(int id_client, String titre, String description, String salaire, String delai, String langage, Timestamp created_at) {
        this.id_client = id_client;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai = delai;
        this.langage = langage;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public int getId_client() {
        return id_client;
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

    public void setId_client(int id_client) {
        this.id_client = id_client;
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

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", client_id=" + id_client + ", titre=" + titre + ", description=" + description + ", salaire=" + salaire + ", delai=" + delai + ", langage=" + langage + ", created_at=" + created_at + '}';
    }

 
}
