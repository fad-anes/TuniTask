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
public class Reponserec {
    private int id ;
    private String nomAgent ;
    private String prenomAgent ;
    private String resolution ;

    public Reponserec (){}
    public Reponserec(int id, String nomAgent, String prenomAgent, String resolution) {
        this.id = id;
        this.nomAgent = nomAgent;
        this.prenomAgent = prenomAgent;
        this.resolution = resolution;
    }

    public Reponserec(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getPrenomAgent() {
        return prenomAgent;
    }

    public void setPrenomAgent(String prenomAgent) {
        this.prenomAgent = prenomAgent;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "Reponserec{" + "id=" + id + ", nomAgent=" + nomAgent + ", prenomAgent=" + prenomAgent + ", resolution=" + resolution + '}';
    }
    
    
}
