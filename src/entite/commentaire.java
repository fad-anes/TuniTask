package entite;

public class commentaire {
    private int idcommentaire	;
    private offre offre_id	;
    private String commentaire;
    private Users userid;
    private String name;
    private String prename;
    private String img;


    public commentaire() {
    }
    public commentaire(String name,String prename,String img,String commentaire) {
        this.name = name;
        this.prename = prename;
        this.img = img;
        this.commentaire = commentaire;

    }
    public commentaire(int idcommentaire,String name,String prename,String img,String commentaire) {
        this.idcommentaire = idcommentaire;
        this.name = name;
        this.prename = prename;
        this.img = img;
        this.commentaire = commentaire;

    }
    public commentaire(int idcommentaire, offre offre_id, Users userid, String name, String prename, String img, String commentaire) {
        this.idcommentaire = idcommentaire;
        this.offre_id = offre_id;
        this.userid = userid;
        this.name = name;
        this.prename = prename;
        this.img = img;
        this.commentaire = commentaire;

    }
    public commentaire(offre offre_id, String commentaire, Users userid) {
        this.offre_id = offre_id;
        this.commentaire = commentaire;
        this.userid = userid;
    }
    public commentaire(offre offre_id, Users userid) {
        this.offre_id = offre_id;
        this.userid = userid;
    }
    public commentaire(int idcommentaire,offre offre_id,String commentaire) {
        this.idcommentaire = idcommentaire;
        this.offre_id = offre_id;
        this.commentaire = commentaire;
    }
    public commentaire(int idcommentaire, offre offre_id, String commentaire, Users userid) {
        this.idcommentaire = idcommentaire;
        this.offre_id = offre_id;
        this.commentaire = commentaire;
        this.userid = userid;
    }


    public int getIdcommentaire() {
        return idcommentaire;
    }
    public offre getOffre_id() {
        return offre_id;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }
    public void setOffre_id(offre offre_id) {
        this.offre_id = offre_id;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public String getPrename() {
        return prename;
    }

    @Override
    public String toString() {
        return "commentaire{" +
                "idcommentaire=" + idcommentaire +
                ", offre_id=" + offre_id +
                ", commentaire='" + commentaire + '\'' +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", prename='" + prename + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
