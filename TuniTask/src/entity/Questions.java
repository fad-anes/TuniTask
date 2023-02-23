package entity;

public class Questions {
    private int id_question;
    private String question_text;
    private Quizs quiz_id;

    public Questions() {
    }
    public Questions(int id_question) {
        this.id_question = id_question;
    }
    public Questions(String question_text, Quizs quiz_id) {
        this.question_text = question_text;
        this.quiz_id = quiz_id;
    }

    public Questions(int id_question, String question_text, Quizs quiz_id) {
        this.id_question = id_question;
        this.question_text = question_text;
        this.quiz_id = quiz_id;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public Quizs getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Quizs quiz_id) {
        this.quiz_id = quiz_id;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id_question=" + id_question +
                ", question_text='" + question_text + '\'' +
                ", quiz_id=" + quiz_id.getId_quiz() +
                '}';
    }
}
