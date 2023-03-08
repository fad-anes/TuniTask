package entite;

public class Quizs {
    private int id_quiz;
    private String quiz_title;
    private String quiz_description;
    private int score;


    public Quizs() {
    }

    public Quizs(String quiz_title, String quiz_description, int score) {
        this.quiz_title = quiz_title;
        this.quiz_description = quiz_description;
        this.score = score;
    }

    public Quizs(int id_quiz, String quiz_title, String quiz_description) {
        this.id_quiz = id_quiz;
        this.quiz_title = quiz_title;
        this.quiz_description = quiz_description;
    }

    public Quizs(String quiz_title, String quiz_description) {
        this.quiz_title = quiz_title;
        this.quiz_description = quiz_description;
    }

    public Quizs(int id_quiz, String quiz_title, String quiz_description, int score) {
        this.id_quiz = id_quiz;
        this.quiz_title = quiz_title;
        this.quiz_description = quiz_description;
        this.score = score;

    }

    public Quizs(int id_quiz) {
        this.id_quiz = id_quiz;
    }


    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getQuiz_description() {
        return
                quiz_description;
    }

    public void setQuiz_description(String quiz_description) {
        this.quiz_description = quiz_description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    @Override
    public String toString() {
        return "Quizs{" +
                "id_quiz=" + id_quiz +
                ", quiz_title='" + quiz_title + '\'' +
                ", quiz_description='" + quiz_description + '\'' +
                ", score=" + score +
                '}';
    }
}

