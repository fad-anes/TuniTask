package entity;

public class Reponses {
   private int id_reponse;
   private String reponse_text;
   private boolean is_correct;
private Questions id_question;

    public Reponses() {
    }

    public Reponses(String reponse_text, boolean is_correct, Questions id_question) {
        this.reponse_text = reponse_text;
        this.is_correct = is_correct;
        this.id_question = id_question;
    }

    public Reponses(int id_reponse, String reponse_text, boolean is_correct, Questions id_question) {
        this.id_reponse = id_reponse;
        this.reponse_text = reponse_text;
        this.is_correct = is_correct;
        this.id_question = id_question;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public String getReponse_text() {
        return reponse_text;
    }

    public void setReponse_text(String reponse_text) {
        this.reponse_text = reponse_text;
    }

    public boolean is_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

    public Questions getId_question() {
        return id_question;
    }

    public void setId_question(Questions id_question) {
        this.id_question = id_question;
    }

    @Override
    public String toString() {
        return "Reponses{" +
                "id_reponse=" + id_reponse +
                ", reponse_text='" + reponse_text + '\'' +
                ", is_correct=" + is_correct +
                ", id_question=" + id_question.getId_question() +
                '}';
    }
}
