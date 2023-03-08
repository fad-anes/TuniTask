package service;

import entite.Answers;

import entite.Questions;


import java.util.List;

public interface IServiceAnswers {

    void insert(Answers answer);

    void delete(Answers answer);

    void update(Answers answer, int id);

    List<Answers> readAll();

    List<Answers> getAnswersByQuestion(Questions question);
}

