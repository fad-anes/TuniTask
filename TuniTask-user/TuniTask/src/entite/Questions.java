package entite;


public class Questions {

    private int id;
    private String question;
    private Quizs quiz;

    public Questions(int id, String question, Quizs quiz) {
        this.id = id;
        this.question = question;
        this.quiz = quiz;
    }
    public Questions(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Quizs getQuiz() {
        return quiz;
    }

    public void setQuiz(Quizs quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", question=" + question + ", quiz=" + quiz + "]";
    }

}
