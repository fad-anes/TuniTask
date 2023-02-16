package entity;

public class Quizs {
    private int quiz_id;
    private String quiz_title;
    private String quiz_description;

    public Quizs(int quiz_id, String quiz_title, String quiz_description) {
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
        this.quiz_description = quiz_description;
    }

    public Quizs(String quiz_title, String quiz_description) {
        this.quiz_title = quiz_title;
        this.quiz_description = quiz_description;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getQuiz_description() {
        return quiz_description;
    }

    public void setQuiz_description(String quiz_description) {
        this.quiz_description = quiz_description;
    }

    @Override
    public String toString() {
        return "Quizs{" +
                "quiz_id=" + quiz_id +
                ", quiz_title='" + quiz_title + '\'' +
                ", quiz_description='" + quiz_description + '\'' +
                '}';
    }
}
