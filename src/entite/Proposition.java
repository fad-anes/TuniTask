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
    private Demande id_demande;
    private int id_freelancer;

    public Proposition() {
    }

    public Proposition(int id, Demande id_demande, int id_freelancer) {
        this.id = id;
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
    }

    
    public Proposition(Demande id_demande, int id_freelancer) {
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
    }

    
    public int getId() {
        return id;
    }

    public Demande getId_demande() {
        return id_demande;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_demande(Demande id_demande) {
        this.id_demande = id_demande;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    @Override
    public String toString() {
        return "Proposition{" + "id=" + id + ", id_demande=" + id_demande.getId() + ", id_freelancer=" + id_freelancer + '}';
    }

}  
