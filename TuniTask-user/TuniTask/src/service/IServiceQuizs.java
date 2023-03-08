package service;

public interface IServiceQuizs<T> {

        void insert(T t);

        void delete(T t);

        void update(T t, int id);

        java.util.List<T> readAll();

        T readById(int i);
    }

