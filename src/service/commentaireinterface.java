package service;
import entite.offre;
import entite.user;

import java.util.List;
public interface commentaireinterface<T> {
    void insert(T t, offre o, user u);
    void delete(int t);
    void update(int t,T c,offre o, user u);
    List<T> readall();
    List<T> ReadById(int id);
    Boolean Findid(int id);
    Boolean Findidcm(int id);
    int Findcomenus(int id);
}
