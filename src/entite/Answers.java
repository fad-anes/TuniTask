package entite;

public class Answers {

    private int id_answer;
    private Questions question_id;
    private Quizs quiz_id;
    private String answer;
    private Boolean is_correct;

    public Answers(int id_answer, Questions question_id, Quizs quiz_id, String answer, Boolean is_correct) {
        this.id_answer = id_answer;
        this.question_id = question_id;
        this.quiz_id = quiz_id;
        this.answer = answer;
        this.is_correct = is_correct;
    }

    public Answers(Questions question_id, Quizs quiz_id, String answer, Boolean is_correct) {
        if (question_id == null) {
            throw new IllegalArgumentException("question_id cannot be null");
        }
        this.question_id = question_id;
        this.quiz_id = quiz_id;
        this.answer = answer;
        this.is_correct = is_correct;
    }

    public Answers() {

    }

    public Answers(String answer, Boolean iscorrect) {
        this.answer = answer;
        this.is_correct = iscorrect;
    }

    public int getId_answer() {
        return id_answer;
    }

    public void setId_answer(int id_answer) {
        this.id_answer = id_answer;
    }

    public Questions getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Questions question_id)
    {
        if (question_id.getQuestion() == null) {
            throw new IllegalArgumentException("question_id cannot be null");
        }
        this.question_id = question_id;
    }

    public Quizs getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Quizs quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        if (is_correct != null) {
            this.is_correct = is_correct;
        }
        else {
            throw new IllegalArgumentException("is_correct cannot be null");
        }
    }

    @Override
    public String toString() {
        return "Answers [id_answer=" + id_answer + ", question_id=" + question_id.getId() + ", quiz_id=" + quiz_id.getId_quiz() + ", answer="
                + answer + ", is_correct=" + is_correct + "]";
    }
}

