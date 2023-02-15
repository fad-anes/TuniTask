package entite;

public class rate {
    private int idrate	;
    private int offre_id	;
    private float rate;

    public rate() {
    }
    public rate(int offre_id,float rate) {
        this.offre_id = offre_id;
        this.rate = rate;
    }
    public rate(int idrate,int offre_id,float rate) {
        this.idrate = idrate;
        this.offre_id = offre_id;
        this.rate = rate;
    }

    public int getIdrate() {
        return idrate;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public float getRate() {
        return rate;
    }

    public void setIdrate(int idrate) {
        this.idrate = idrate;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
