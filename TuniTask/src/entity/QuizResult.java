package entity;

public class QuizResult {
    private int result_id;
    private int quiz_id;
    private int freelancer_id;
    private int score;

    public QuizResult(int result_id, int quiz_id, int freelancer_id, int score) {
        this.result_id = result_id;
        this.quiz_id = quiz_id;
        this.freelancer_id = freelancer_id;
        this.score = score;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(int freelancer_id) {
        this.freelancer_id = freelancer_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "result_id=" + result_id +
                ", quiz_id=" + quiz_id +
                ", freelancer_id=" + freelancer_id +
                ", score=" + score +
                '}';
    }
}
