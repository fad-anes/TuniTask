package entite;

public class offre {
    private int idoffre	;
    private int freelancer_id	;
    private String description;
    private String titre;
    private float salaireH;
    private String skills;
    private String country;
    private String role;
    private String language;
    private String nom;
    private String prenom;
    private int years;

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
    public offre(int idoffre,int freelancer_id,String description,String titre,float salaireH,String skills,String country,String role,String language,int years,String nom,String prenom) {
        this.idoffre = idoffre;
        this.freelancer_id = freelancer_id;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
        this.skills = skills;
        this.country = country;
        this.role = role;
        this.language = language;
        this.years = years;
        this.nom = nom;
        this.prenom = prenom;
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

    public String getSkills() {
        return skills;
    }

    public String getCountry() {
        return country;
    }

    public String getRole() {
        return role;
    }

    public String getLanguage() {
        return language;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getYears() {
        return years;
    }

    @Override
    public String toString() {
        return "offre{" +
                "idoffre=" + idoffre +
                ", freelancer_id=" + freelancer_id +
                ", description='" + description + '\'' +
                ", titre='" + titre + '\'' +
                ", salaireH=" + salaireH +
                ", skills='" + skills + '\'' +
                ", country='" + country + '\'' +
                ", role='" + role + '\'' +
                ", language='" + language + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", years=" + years +
                '}';
    }
}
