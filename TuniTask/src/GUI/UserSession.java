
package GUI;

import entity.Questions;
import entity.Quizs;

import java.io.Serializable;
import java.util.Objects;


public class UserSession implements Serializable {



    private Quizs id_quiz;
    private Questions id_question;
    private static UserSession instance;

   private UserSession() {}

   public static synchronized UserSession getInstance() {
      if (instance == null) {
         instance = new UserSession();
      }
      return instance;
   }

    public Quizs getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(Quizs id_quiz) {
        this.id_quiz = id_quiz;
    }

    public Questions getId_question() {
        return id_question;
    }

    public void setId_question(Questions id_question) {
        this.id_question = id_question;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return id_quiz.equals(that.id_quiz) && id_question.equals(that.id_question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_quiz, id_question);
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id_quiz=" + id_quiz +
                ", id_question=" + id_question +
                '}';
    }
}
