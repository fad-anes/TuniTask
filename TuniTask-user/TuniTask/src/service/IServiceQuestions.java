package service;

import entite.Questions;

import java.util.List;

public interface IServiceQuestions<T> {
    void insert(T t);

    void delete(T t);

    void update(T t, int id);

     List<T> readAll();

    List<T> readByQuizId(int quizId);
}
