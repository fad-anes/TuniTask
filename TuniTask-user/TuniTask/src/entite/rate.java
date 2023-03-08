package entite;

public class rate {
    private int idrate	;
    private offre offre_id	;
    private Users users_id;
    private int rate;

    public rate() {
    }
    public rate(offre offre_id, Users users_id, int rate) {
        this.offre_id = offre_id;
        this.users_id = users_id;
        this.rate = rate;
    }
    public rate(int idrate,offre offre_id,int rate) {
        this.idrate = idrate;
        this.offre_id = offre_id;
        this.rate = rate;
    }

    public int getIdrate() {
        return idrate;
    }

    public offre getOffre_id() {
        return offre_id;
    }

    public int getRate() {
        return rate;
    }

    public void setIdrate(int idrate) {
        this.idrate = idrate;
    }

    public void setOffre_id(offre offre_id) {
        this.offre_id = offre_id;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Users getUser_id() {
        return users_id;
    }

    public void setUser_id(Users users_id) {
        this.users_id = users_id;
    }

    @Override
    public String toString() {
        return "rate{" +
                "idrate=" + idrate +
                ", offre_id=" + offre_id +
                ", user_id=" + users_id +
                ", rate=" + rate +
                '}';
    }
}
