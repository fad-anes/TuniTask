package entite;

public class offre {
    private int idoffre	;
    private int freelancer_id	;
    private String description;
    private String titre;
    private float salaireH;

    public offre() {
    }
    public offre(int freelancer_id,String description,String titre,float salaireH) {
        this.freelancer_id = freelancer_id;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
    }
    public offre(int idoffre,int freelancer_id,String description,String titre,float salaireH) {
        this.idoffre = idoffre;
        this.freelancer_id = freelancer_id;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
    }

    public int getIdoffre() {
        return idoffre;
    }

    public int getFreelancer_id() {
        return freelancer_id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitre() {
        return titre;
    }

    public float getSalaireH() {
        return salaireH;
    }

    public void setIdoffre(int idoffre) {
        this.idoffre = idoffre;
    }

    public void setFreelancer_id(int freelancer_id) {
        this.freelancer_id = freelancer_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setSalaireH(float salaireH) {
        this.salaireH = salaireH;
    }
}
