package service;

import java.util.List;

public interface IServiceDemande<T> {
    void insert(T t);
    void delete(T t);
    void update(T t,int id);
    //void update(T t);
    List<T> readAll();
    T readById(int id);
    public List<T> readByIdClient(int id_client);
}
