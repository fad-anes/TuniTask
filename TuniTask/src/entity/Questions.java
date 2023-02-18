package entity;

public class Questions {
    private int id_question;
    private String question_text;
    private Quizs quiz;

    public Questions() {
    }
    public Questions(int id_question) {
        this.id_question = id_question;
    }
    public Questions(String question_text, Quizs quiz) {
        this.question_text = question_text;
        this.quiz = quiz;
    }

    public Questions(int id_question, String question_text, Quizs quiz) {
        this.id_question = id_question;
        this.question_text = question_text;
        this.quiz = quiz;
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

    public Quizs quiz() {
        return quiz;
    }

    public void setQuiz_id(Quizs quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id_question=" + id_question +
                ", question_text='" + question_text + '\'' +
                ", quiz_id=" + quiz.getId_quiz() +
                '}';
    }
}
