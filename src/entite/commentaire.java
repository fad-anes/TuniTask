package entite;

public class commentaire {
    private int idcommentaire	;
    private int offre_id	;
    private String commentaire;
    private String titre;


    public commentaire() {
    }
    public commentaire(int offre_id,String commentaire,String titre) {
        this.offre_id = offre_id;
        this.commentaire = commentaire;
        this.titre = titre;
    }
    public commentaire(int idcommentaire,int offre_id,String commentaire) {
        this.idcommentaire = idcommentaire;
        this.offre_id = offre_id;
        this.commentaire = commentaire;
    }
    public commentaire(int idcommentaire,int offre_id,String commentaire,String titre) {
        this.idcommentaire = idcommentaire;
        this.offre_id = offre_id;
        this.commentaire = commentaire;
        this.titre = titre;
    }
    public commentaire(int offre_id,String commentaire) {

        this.offre_id = offre_id;
        this.commentaire = commentaire;

    }

    public int getIdcommentaire() {
        return idcommentaire;
    }
    public int getOffre_id() {
        return offre_id;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }
    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "commentaire{" +
                "idcommentaire=" + idcommentaire +
                ", offre_id=" + offre_id +
                ", commentaire='" + commentaire + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }
}
