package service;

public interface IServiceQuestions<T> {
    void insert(T t);

    void delete(T t);

    void update(T t, int id);

    java.util.List<T> readAll();

    T readById(int id);
}
