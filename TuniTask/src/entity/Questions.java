package entity;

public class Questions {
    private int question_id;
    private Quizs quiz_id;
    private String question_text;
    private String answer_options;
    private String correct_answer;

    public Questions(int question_id, Quizs quiz_id, String question_text, String answer_options, String correct_answer) {
        this.question_id = question_id;
        this.quiz_id = quiz_id;
        this.question_text = question_text;
        this.answer_options = answer_options;
        this.correct_answer = correct_answer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public Quizs getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Quizs quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getAnswer_options() {
        return answer_options;
    }

    public void setAnswer_options(String answer_options) {
        this.answer_options = answer_options;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question_id=" + question_id +
                ", quiz_id=" + quiz_id +
                ", question_text='" + question_text + '\'' +
                ", answer_options='" + answer_options + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                '}';
    }
}
