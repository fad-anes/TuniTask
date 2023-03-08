package entite;

public class offre {
    private int idoffre	;
    private Users users_id;
    private String description;
    private String titre;
    private float salaireH;
    private String email;
    private String img;
    private String nom;
    private String prenom;




    public void setUser_id(Users users_id) {
        this.users_id = users_id;
    }

    public offre() {

    }
    public offre(int idoffre) {
        this.idoffre = idoffre;
    }
    public offre(Users users_id, String description, String titre, float salaireH) {
        this.users_id = users_id;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
    }

    public offre(int idoffre, Users users_id, String description, String titre, float salaireH) {
        this.idoffre = idoffre;
        this.users_id = users_id;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
    }
    public offre(int idoffre, Users Users, String description, String titre, float salaireH, String nom, String prenom, String email, String img) {
        this.users_id = Users;
        this.idoffre = idoffre;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.img = img;
    }
    public offre(String description,String titre,float salaireH) {

        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.img = img;
    }
    public offre(String description, String titre, float salaireH, Users users_id) {
        this.users_id = users_id;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.img = img;
    }
    public offre(int idoffre,String description,String titre,float salaireH) {
        this.idoffre = idoffre;
        this.description = description;
        this.titre = titre;
        this.salaireH = salaireH;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.img = img;
    }
    public int getIdoffre() {
        return idoffre;
    }

    public Users getUser_id() {
        return users_id;
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

    public void setFreelancer_id(Users users_id) {
        this.users_id = users_id;
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


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "offre{" +
                "idoffre=" + idoffre +
                ", user_id=" + users_id +
                ", description='" + description + '\'' +
                ", titre='" + titre + '\'' +
                ", salaireH=" + salaireH +
                ", email='" + email + '\'' +
                ", img='" + img + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
